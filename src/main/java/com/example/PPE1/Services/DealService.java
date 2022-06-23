package com.example.PPE1.Services;

import com.example.PPE1.DAO.DealRepository;
import com.example.PPE1.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {
	@Autowired
	DealRepository dr;

	public List<Deal> getAll() {
		return dr.findAll();
	}

	public Deal save(Deal deal) {
		return dr.save(deal);
	}

	public void removeById(Long id) {
		dr.deleteById(id);
	}

	public Optional<Deal> getDealById(Long id) {
		return dr.findById(id);
	}

	public List<Deal> getAllByCategorieId(Long id) {
		return dr.findAllByCategorieId(id);
	}

}
