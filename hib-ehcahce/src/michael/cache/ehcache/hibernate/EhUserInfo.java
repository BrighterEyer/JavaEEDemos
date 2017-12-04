package michael.cache.ehcache.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MY_TB_EH_USER_INFO")
public class EhUserInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 930384253681679239L;

	private Integer id;

	private String userId;

	private String userName;

	private String otherInfo;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

}