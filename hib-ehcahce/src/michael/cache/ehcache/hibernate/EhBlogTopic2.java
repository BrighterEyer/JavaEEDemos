package michael.cache.ehcache.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "MY_TB_EH_BLOG_TOPIC2")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EhBlogTopic2 implements Serializable {
	// 属性和EhBlogTopic一样
	// 其他省略
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userId;

	private String topic;

	private String site;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	@Column
	public String getUserId() {
		return userId;
	}

	@Column
	public String getTopic() {
		return topic;
	}

	@Column
	public String getSite() {
		return site;
	}
}