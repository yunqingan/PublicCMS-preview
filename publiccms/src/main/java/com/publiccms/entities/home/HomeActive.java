package com.publiccms.entities.home;
// Generated 2016-11-12 18:33:49 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sanluan.common.generator.annotation.GeneratorColumn;

/**
 * HomeActive generated by hbm2java
 */
@Entity
@Table(name = "home_active")
public class HomeActive implements java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @GeneratorColumn(title = "ID")
    private Long id;
    @GeneratorColumn(title = "项目类型", condition = true)
    private String itemType;
    @GeneratorColumn(title = "项目ID")
    private long itemId;
    @GeneratorColumn(title = "用户", condition = true)
    private long userId;
    @GeneratorColumn(title = "创建日期")
    private Date createDate;

    public HomeActive() {
    }

    public HomeActive(String itemType, long itemId, long userId, Date createDate) {
        this.itemType = itemType;
        this.itemId = itemId;
        this.userId = userId;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "item_type", nullable = false, length = 20)
    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Column(name = "item_id", nullable = false)
    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
