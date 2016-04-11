/**
 * 
 */
package works.tonny.apps.workflow;

import org.llama.library.utils.ThreadLocalMap;

import works.tonny.apps.user.AbstractUser;
import works.tonny.apps.user.LoginedUser;

/**
 * @author 祥栋
 */
public class RuntimeVariables {
	/**
	 * 当前登录用户
	 * 
	 * @return
	 */
	public AbstractUser loginedUser() {
		LoginedUser object = ThreadLocalMap.getInstance().getObject(LoginedUser.class);
		return object.getUser();
	}
}
