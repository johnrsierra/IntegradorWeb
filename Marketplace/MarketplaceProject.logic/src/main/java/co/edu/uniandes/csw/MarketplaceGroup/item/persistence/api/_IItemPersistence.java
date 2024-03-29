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

package co.edu.uniandes.csw.MarketplaceGroup.item.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.MarketplaceGroup.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.MarketplaceGroup.item.logic.dto.ItemPageDTO;

public interface _IItemPersistence {

	public ItemDTO createItem(ItemDTO detail);
	public List<ItemDTO> getItems();
	public ItemPageDTO getItems(Integer page, Integer maxRecords);
	public ItemDTO getItem(Long id);
	public void deleteItem(Long id);
	public void updateItem(ItemDTO detail);
	
}