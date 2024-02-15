package com.alchemyalley.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.alchemyalley.api.persistence.ProductDAO;
import com.alchemyalley.api.model.Product;

@RestController
@RequestMapping("inventory")
public class InventoryController {

	private static final Logger LOG = Logger.getLogger(InventoryController.class.getName());
	private final ProductDAO productDAO;

	public InventoryController(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getHero(@PathVariable int id) {
		/*
		 * LOG.info("GET /heroes/" + id);
		 * try {
		 * Product hero = productDAO.getHero(id);
		 * return hero != null ?
		 * new ResponseEntity<>(hero, HttpStatus.OK) :
		 * new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 * } catch(IOException e) {
		 * LOG.log(Level.SEVERE,e.getLocalizedMessage());
		 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 * }
		 */
		return null;
	}

	@GetMapping("")
	public ResponseEntity<Product[]> getHeroes() {
		/*
		 * LOG.info("GET /heroes");
		 * 
		 * try {
		 * Product[] heroes = productDAO.getHeroes();
		 * return new ResponseEntity<>(heroes, HttpStatus.OK);
		 * } catch (IOException e) {
		 * LOG.log(Level.SEVERE, e.getLocalizedMessage());
		 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 * }
		 */
		return null;
	}

	@GetMapping("/products")
	public ResponseEntity<Product[]> searchProducts(@RequestParam String name) {
		LOG.info("GET /inventory/products?name=" + name);

		try {
			Product[] products = productDAO.findProducts(name);
			if (products.length > 0) {
				return new ResponseEntity<>(products, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        LOG.info("PUT /inventory/products " + product);

        try {
            Product updated = this.productDAO.updateProduct(product);

	        return updated != null ?
                    new ResponseEntity<>(updated, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        LOG.info("POST /inventory/products " + product);

        try {
            Product created = productDAO.createProduct(product);

            if (created== null || productDAO.getProduct(product.getId()) != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(created,HttpStatus.CREATED);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteHero(@PathVariable int id) {
        LOG.info("DELETE /heroes/" + id);

	    try {
		    boolean existed = productDAO.deleteProduct(id);
            return existed ?
                    new ResponseEntity<>(HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }

}
