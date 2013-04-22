/**
 * 
 */
package com.onakaumi.akiumi.dao.impl;

import org.springframework.stereotype.Repository;

import com.onakaumi.akiumi.dao.VideoDao;
import com.onakaumi.akiumi.model.Video;
import com.onakaumi.core.base.dao.hibernate.GenericDAOHibernateImpl;

/**
 * @author Onakaumi
 * @since 2013-4-23 上午12:24:35
 * 
 */
@Repository("videoDao")
public class VideoDaoImpl extends GenericDAOHibernateImpl<Video, Integer>
		implements VideoDao {

}
