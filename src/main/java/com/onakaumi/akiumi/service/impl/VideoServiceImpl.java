/**
 * 
 */
package com.onakaumi.akiumi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.onakaumi.akiumi.dao.VideoDao;
import com.onakaumi.akiumi.model.Video;
import com.onakaumi.akiumi.service.VideoService;
import com.onakaumi.core.base.dao.IGenericDAO;
import com.onakaumi.core.base.service.AbstractService;

/**
 * @author Onakaumi
 * @since 2013-4-23 上午12:21:39
 * 
 */
@Service("videoService")
public class VideoServiceImpl extends AbstractService<Video, Integer> implements
		VideoService {

	private VideoDao videoDao;

	@Resource
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	protected IGenericDAO<Video, Integer> dao() {
		return videoDao;
	}

}
