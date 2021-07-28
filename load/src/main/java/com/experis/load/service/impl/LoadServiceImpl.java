package com.experis.load.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.experis.load.LoadApplication;
import com.experis.load.entity.ProductEntity;
import com.experis.load.repository.ProductsRepository;
import com.experis.load.service.LoadService;

@Service
public class LoadServiceImpl implements LoadService {
	
	@Autowired
	ProductsRepository repo;
	
	Logger LOGGER = LoggerFactory.getLogger(LoadApplication.class);
	
	@Override
	public void loadTable() {
	
		try {
			LOGGER.info("ejecuntando job de carga!!!!");
			repo.deleteAll();
			
			Resource resource = new ClassPathResource("Productos.csv");
			InputStream inputStream = resource.getInputStream();
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			CSVParser csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			csvRecords.forEach( csvRecord -> {
				String porcentajeDescuento = csvRecord.get("porcentaje_descuento");
				String precio = csvRecord.get("precio");
				String cantidadStock = csvRecord.get("cantidad_en_stock");
				String estado = csvRecord.get("estado");
				String nombre = csvRecord.get("nombre");
				String marca = csvRecord.get("marca");
				
				boolean valid = validfields(porcentajeDescuento, precio, cantidadStock, estado, nombre, marca);
				if(valid) {
					ProductEntity product = new ProductEntity();
					product.setNombre(nombre);
					product.setMarca(marca);
					product.setPrecio(Long.parseLong(precio));
					product.setStock(Long.parseLong(cantidadStock));
					product.setEstado(estado);
					product.setDescuento(Integer.parseInt(porcentajeDescuento));
					listProducts.add(product);
				}
			});
			
			repo.saveAll(listProducts);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}	
	}
	
	
	private boolean validfields(String ... params) {
		for (String string : params) {
			if(string.equals(""))
				return false;
		}
		return true;
	}
	
}
