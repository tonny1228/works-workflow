/**
 * 
 */
package works.tonny.apps.workflow.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.llama.library.utils.ClassUtils;
import org.llama.library.utils.ConvertUtils;
import org.llama.library.utils.HttpRequestUtils;

import works.tonny.apps.workflow.WFState;
import works.tonny.apps.workflow.WorkflowEntity;

/**
 * @author 祥栋
 */
public class VariableUtils {
	private static final List<String> LIST = Arrays.asList("processDefinitionId", "taskId", "processInstanceId");

	/**
	 * 数组转为map参数
	 * 
	 * @param id 表单数据id
	 * @return
	 */
	public static Map<String, Object> convertFromArray(Object[] param) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (param != null && param.length % 2 != 0) {
			throw new IllegalArgumentException("错误的参数个数");
		}

		for (int i = 0; param != null && i < param.length; i++) {
			if (i % 2 == 0 && (!(param[i] instanceof String) || param[i] == null)) {
				throw new IllegalArgumentException("错误的参数名");
			}
			params.put((String) param[i], param[++i]);
		}
		return params;
	}

	/**
	 * 数组转为set参数名
	 * 
	 * @param variableNames
	 * @return
	 */
	public static Set<String> variableNamesFromArray(String[] variableNames) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; variableNames != null && i < variableNames.length; i++) {
			set.add(variableNames[i]);
		}
		return set;
	}

	/**
	 * 将request的参数封装为指定的类
	 * 
	 * @param request
	 * @param clz
	 * @return
	 */
	public static WorkflowEntity wrap(HttpServletRequest request, String entityClass) {
		WorkflowEntity entity = null;
		if (entityClass != null) {
			try {
				entity = (WorkflowEntity) HttpRequestUtils.createBean(request, ClassUtils.getClass(entityClass));
				entity.setProcessStartTime(new Date());
				entity.setState(WFState.进行中);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
		return entity;
	}

	/**
	 * 填充参数数据，过滤表单数据和引擎数据
	 * 
	 * @param newVariables 待填充的map
	 * @param request http请求
	 * @param entity 实体类，类中的属性过滤掉
	 * @param variables 原有的变量，新的变量转换原有的类型
	 */
	public static void fillVariables(Map<String, Object> newVariables, HttpServletRequest request,
			WorkflowEntity entity, Map<String, Object> variables) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			if (LIST.contains(name)) {
				continue;
			}
			if (!name.contains(".") && entity != null && PropertyUtils.isWriteable(entity, name)) {
				continue;
			}
			if (name.contains(".") && entity != null
					&& PropertyUtils.isWriteable(entity, StringUtils.substringBefore(name, "."))) {
				continue;
			}
			if (name.contains(".") && entity != null
					&& PropertyUtils.isWriteable(entity, StringUtils.substringAfter(name, "."))) {
				continue;
			}
			Object arg = null;
			if (variables != null)
				arg = variables.get(name);
			String[] values = request.getParameterValues(name);
			if (values == null || values.length != 1) {
				if (arg == null) {
					newVariables.put(name, values);
				} else {
					newVariables.put(name, ConvertUtils.convert(arg.getClass(), values));
				}
			} else {
				newVariables.put(name, arg == null ? values[0] : ConvertUtils.convert(arg.getClass(), values[0]));
			}
		}
	}
}
