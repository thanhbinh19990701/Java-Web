package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Slider;
import com.web.repository.ISliderRepository;
import com.web.services.ISliderService;
@Service
public class SliderService implements ISliderService{

	@Autowired
	ISliderRepository sliderrepository;
	@Override
	public List<Slider> findAll() {
		return sliderrepository.findAll();
	}

	@Override
	public Optional<Slider> findById(Integer id) {
		Optional<Slider> option = sliderrepository.findById(id);
		return option;
	}

	@Override
	public Slider create(Slider slider) {
		sliderrepository.save(slider);
		return slider;
	}

	@Override
	public Slider update(Slider slider) {
		sliderrepository.save(slider);
		return slider;
	}

	@Override
	public void delete(Integer id) {
		Optional<Slider> delete = sliderrepository.findById(id);
		Slider slider = delete.get();
		if (slider !=null) {
			sliderrepository.delete(slider);
		}
	}

}
