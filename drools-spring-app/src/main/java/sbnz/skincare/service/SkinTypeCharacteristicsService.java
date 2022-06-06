package sbnz.skincare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.skincare.facts.SkinTypeCharacteristics;
import sbnz.skincare.repository.SkinTypeCharacteristicsRepository;

@Service
public class SkinTypeCharacteristicsService {

	private SkinTypeCharacteristicsRepository skinTypeCharacteristicsRepository;

	@Autowired
	public SkinTypeCharacteristicsService(SkinTypeCharacteristicsRepository skinTypeCharacteristicsRepository) {
		this.skinTypeCharacteristicsRepository = skinTypeCharacteristicsRepository;
	}

	public List<SkinTypeCharacteristics> getAll() {
		return this.skinTypeCharacteristicsRepository.findAll();
	}
}
