package works.tonny.apps.workflow;

import works.tonny.apps.user.AbstractUser;

/**
 * Created by tonny on 2015/11/13.
 */
public interface WorkflowExecutorFactory {
    WorkflowExecutor valueOf(AbstractUser user);
}
