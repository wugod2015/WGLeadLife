package com.jackhan.wgleadlife.bean;

import java.util.List;
import com.jackhan.wgleadlife.db.DaoSession;
import com.jackhan.wgleadlife.db.WeatherDao;
import com.jackhan.wgleadlife.db.WeatherResultDao;
import com.jackhan.wgleadlife.db.Weather_IndexDao;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "WEATHER_RESULT".
 */
public class WeatherResult {

    /** Not-null value. */
    private String currentCity;
    private String pm25;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient WeatherResultDao myDao;

    private List<Weather> weather_data;
    private List<Weather_Index> index;

    public WeatherResult() {
    }

    public WeatherResult(String currentCity) {
        this.currentCity = currentCity;
    }

    public WeatherResult(String currentCity, String pm25) {
        this.currentCity = currentCity;
        this.pm25 = pm25;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWeatherResultDao() : null;
    }

    /** Not-null value. */
    public String getCurrentCity() {
        return currentCity;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Weather> getWeather_data() {
        if (weather_data == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WeatherDao targetDao = daoSession.getWeatherDao();
            List<Weather> weather_dataNew = targetDao._queryWeatherResult_Weather_data(currentCity);
            synchronized (this) {
                if(weather_data == null) {
                    weather_data = weather_dataNew;
                }
            }
        }
        return weather_data;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetWeather_data() {
        weather_data = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Weather_Index> getIndex() {
        if (index == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Weather_IndexDao targetDao = daoSession.getWeather_IndexDao();
            List<Weather_Index> indexNew = targetDao._queryWeatherResult_Index(currentCity);
            synchronized (this) {
                if(index == null) {
                    index = indexNew;
                }
            }
        }
        return index;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetIndex() {
        index = null;
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
