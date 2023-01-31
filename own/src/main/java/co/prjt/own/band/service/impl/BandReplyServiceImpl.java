package co.prjt.own.band.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandReplyMapper;
import co.prjt.own.band.service.BandReplyService;

@Service
public class BandReplyServiceImpl implements BandReplyService{
	
	@Autowired BandReplyMapper bandReplyMapper;
	
}
