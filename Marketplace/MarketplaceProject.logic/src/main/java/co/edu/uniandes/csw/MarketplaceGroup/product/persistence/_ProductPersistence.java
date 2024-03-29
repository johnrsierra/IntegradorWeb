/* ========================================================================
 * Copyright 2014 MarketplaceGroup
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 MarketplaceGroup

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410141434

*/

package co.edu.uniandes.csw.MarketplaceGroup.product.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.MarketplaceGroup.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.MarketplaceGroup.product.logic.dto.ProductPageDTO;
import co.edu.uniandes.csw.MarketplaceGroup.product.persistence.api._IProductPersistence;
import co.edu.uniandes.csw.MarketplaceGroup.product.persistence.converter.ProductConverter;
import co.edu.uniandes.csw.MarketplaceGroup.product.persistence.entity.ProductEntity;

public abstract class _ProductPersistence implements _IProductPersistence {

  	@PersistenceContext(unitName="MarketplaceProjectPU")
 
	protected EntityManager entityManager;
	
	public ProductDTO createProduct(ProductDTO product) {
		ProductEntity entity=ProductConverter.persistenceDTO2Entity(product);
		entityManager.persist(entity);
		return ProductConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductDTO> getProducts() {
		Query q = entityManager.createQuery("select u from ProductEntity u");
		return ProductConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public ProductPageDTO getProducts(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from ProductEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from ProductEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		ProductPageDTO response = new ProductPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(ProductConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public ProductDTO getProduct(Long id) {
		return ProductConverter.entity2PersistenceDTO(entityManager.find(ProductEntity.class, id));
	}

	public void deleteProduct(Long id) {
		ProductEntity entity=entityManager.find(ProductEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateProduct(ProductDTO detail) {
		ProductEntity entity=entityManager.merge(ProductConverter.persistenceDTO2Entity(detail));
		ProductConverter.entity2PersistenceDTO(entity);
	}

}