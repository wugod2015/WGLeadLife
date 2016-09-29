package com.jackhan.wgleadlife.bean;

import com.jackhan.wgleadlife.db.DaoSession;
import com.jackhan.wgleadlife.db.LeadPlanDao;
import com.jackhan.wgleadlife.db.LeadRecordDao;
import com.jackhan.wgleadlife.db.PlanRecordDao;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PLAN_RECORD".
 */
public class PlanRecord {

    private String plan_id;
    private String record_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PlanRecordDao myDao;

    private LeadPlan leadPlan;
    private String leadPlan__resolvedKey;

    private LeadRecord leadRecord;
    private String leadRecord__resolvedKey;


    public PlanRecord() {
    }

    public PlanRecord(String plan_id, String record_id) {
        this.plan_id = plan_id;
        this.record_id = record_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanRecordDao() : null;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    /** To-one relationship, resolved on first access. */
    public LeadPlan getLeadPlan() {
        String __key = this.plan_id;
        if (leadPlan__resolvedKey == null || leadPlan__resolvedKey != __key) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeadPlanDao targetDao = daoSession.getLeadPlanDao();
            LeadPlan leadPlanNew = targetDao.load(__key);
            synchronized (this) {
                leadPlan = leadPlanNew;
            	leadPlan__resolvedKey = __key;
            }
        }
        return leadPlan;
    }

    public void setLeadPlan(LeadPlan leadPlan) {
        synchronized (this) {
            this.leadPlan = leadPlan;
            plan_id = leadPlan == null ? null : leadPlan.getPlan_id();
            leadPlan__resolvedKey = plan_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public LeadRecord getLeadRecord() {
        String __key = this.record_id;
        if (leadRecord__resolvedKey == null || leadRecord__resolvedKey != __key) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeadRecordDao targetDao = daoSession.getLeadRecordDao();
            LeadRecord leadRecordNew = targetDao.load(__key);
            synchronized (this) {
                leadRecord = leadRecordNew;
            	leadRecord__resolvedKey = __key;
            }
        }
        return leadRecord;
    }

    public void setLeadRecord(LeadRecord leadRecord) {
        synchronized (this) {
            this.leadRecord = leadRecord;
            record_id = leadRecord == null ? null : leadRecord.getRecord_id();
            leadRecord__resolvedKey = record_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
