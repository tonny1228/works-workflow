package works.tonny.apps.workflow;

import works.tonny.apps.user.AbstractUser;

import java.io.Serializable;

/**
 * Created by tonny on 2015/11/12.
 */
public class WorkflowExecutor implements Serializable {
    /**
     * 用户身份，办理流程都用它
     */
    private String id;
    private String name;
    private String group;
    private String loginName;
    private String info;

    public WorkflowExecutor(String id, String name, String loginName, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.loginName = loginName;
    }


    public static WorkflowExecutor valueOf(AbstractUser user) {
        return new WorkflowExecutor(user.getUsername(), user.getName(), user.getUsername(), null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
