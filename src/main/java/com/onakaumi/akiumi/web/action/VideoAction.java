package com.onakaumi.akiumi.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.onakaumi.akiumi.model.Video;
import com.onakaumi.akiumi.service.VideoService;
import com.onakaumi.core.base.service.IService;
import com.onakaumi.core.base.web.action.BaseAction;

/**
 * @author Onakaumi
 * @since 2013-4-14 下午11:38:55
 * 
 */
@Controller
@Scope("prototype")
public class VideoAction extends BaseAction<Video, Integer> {
	private static final long serialVersionUID = 1L;

	private VideoService videoService;

	@Resource
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	@Override
	protected IService<Video, Integer> service() {
		return videoService;
	}

}
