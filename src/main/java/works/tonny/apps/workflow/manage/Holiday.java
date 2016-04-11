/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-29 上午9:57:50
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import works.tonny.apps.Entity;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
@javax.persistence.Entity
@Table(name = "wf_cfg_holiday")
public class Holiday extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date date;

	private String name;

	private Date createDate;

	/**
	 * 0 是假日，1是上班日
	 */
	private int type;

	/**
	 * @see works.tonny.apps.Entity#getId()
	 */
	@Override
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 50)
	public String getId() {
		return super.getId();
	}

	/**
	 * @return the date
	 */
	@Column(name = "holiday_date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the name
	 */
	@Column(length = 50)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createDate
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
