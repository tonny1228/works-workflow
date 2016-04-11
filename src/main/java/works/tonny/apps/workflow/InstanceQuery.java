package works.tonny.apps.workflow;

import org.llama.library.utils.PagedList;

/**
 * Created by tonny on 2015/11/18.
 */
public interface InstanceQuery {
    /**
     * 用户发起的
     * @return
     */
    InstanceQuery started();

    /**
     * 用户
     * @return
     */
    InstanceQuery unclaimed();

    InstanceQuery involved();

    InstanceQuery dealed();

    InstanceQuery userToDeal();

    InstanceQuery finished();

    InstanceQuery unfinished();

    InstanceQuery processDefinitionKey(String processDefinitionKey);

    InstanceQuery processDefinitionId(String processDefinitionId);

    InstanceQuery involvedUser(String userId);

    InstanceQuery variableValueEquals(String name, Object value);

    InstanceQuery variableValueGreater(String name, Object value);

    InstanceQuery variableValueLess(String name, Object value);

    InstanceQuery variableValueGreaterOrEquals(String name, Object value);

    InstanceQuery variableValueLessOrEquals(String name, Object value);

    InstanceQuery variableValueLike(String name, String value);

    PagedList list(int offset, int limit);


}
