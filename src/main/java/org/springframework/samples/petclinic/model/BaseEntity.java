package org.springframework.samples.petclinic.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * ベースエンティティクラス
 * idプロパティを持つシンプルなJavaBeanドメインオブジェクト。このプロパティが必要なオブジェクトの基本クラスとして使用されます。
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * IDを取得する
	 * 
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * IDを設定する
	 * 
	 * @param id ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * エンティティが新規かどうかを判定する
	 * 
	 * @return 新規エンティティの場合はtrue、そうでない場合はfalse
	 */
	public boolean isNew() {
		return this.id == null;
	}

}