package com.jackhan.wgleadlife.bean;

import java.util.List;

import com.jackhan.wgleadlife.db.DaoSession;
import com.jackhan.wgleadlife.db.LeadPlanDao;
import com.jackhan.wgleadlife.db.PlanRecordDao;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table "LEAD_PLAN".
 */
public class LeadPlan {

    /**
     * Not-null value.
     */
    private String plan_id;
    private java.util.Date start_date;
    private java.util.Date end_date;
    private String quantity;
    private String unit;

    /**
     * Used to resolve relations
     */
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    private transient LeadPlanDao myDao;

    private List<PlanRecord> planRecordList;

    public LeadPlan() {
    }

    public LeadPlan(String plan_id) {
        this.plan_id = plan_id;
    }

    public LeadPlan(String plan_id, java.util.Date start_date, java.util.Date end_date, String quantity, String unit) {
        this.plan_id = plan_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLeadPlanDao() : null;
    }

    /**
     * Not-null value.
     */
    public String getPlan_id() {
        return plan_id;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public java.util.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(java.util.Date start_date) {
        this.start_date = start_date;
    }

    public java.util.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(java.util.Date end_date) {
        this.end_date = end_date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity.
     */
    public List<PlanRecord> getPlanRecordList() {
        if (planRecordList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlanRecordDao targetDao = daoSession.getPlanRecordDao();
            List<PlanRecord> planRecordListNew = targetDao._queryLeadPlan_PlanRecordList(plan_id);
            synchronized (this) {
                if (planRecordList == null) {
                    planRecordList = planRecordListNew;
                }
            }
        }
        return planRecordList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    public synchronized void resetPlanRecordList() {
        planRecordList = null;
    }

    /**
     * Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context.
     */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context.
     */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context.
     */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

}
