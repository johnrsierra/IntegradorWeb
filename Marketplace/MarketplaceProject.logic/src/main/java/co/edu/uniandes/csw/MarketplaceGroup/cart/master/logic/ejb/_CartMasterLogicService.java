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


Source generated by CrudMaker version 1.0.0.qualifier

*/

package co.edu.uniandes.csw.MarketplaceGroup.cart.master.logic.ejb;

import co.edu.uniandes.csw.MarketplaceGroup.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.MarketplaceGroup.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.MarketplaceGroup.cart.logic.dto.CartDTO;
import co.edu.uniandes.csw.MarketplaceGroup.cart.master.logic.api._ICartMasterLogicService;
import co.edu.uniandes.csw.MarketplaceGroup.cart.master.logic.dto.CartMasterDTO;
import co.edu.uniandes.csw.MarketplaceGroup.cart.master.persistence.api.ICartMasterPersistence;
import co.edu.uniandes.csw.MarketplaceGroup.cart.master.persistence.entity.CartitemEntity;
import co.edu.uniandes.csw.MarketplaceGroup.cart.persistence.api.ICartPersistence;
import javax.inject.Inject;

public abstract class _CartMasterLogicService implements _ICartMasterLogicService {

    @Inject
    protected ICartPersistence cartPersistance;
    @Inject
    protected ICartMasterPersistence cartMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;

    public CartMasterDTO createMasterCart(CartMasterDTO cart) {
        CartDTO persistedCartDTO = cartPersistance.createCart(cart.getCartEntity());
        if (cart.getCreateitem() != null) {
            for (ItemDTO itemDTO : cart.getCreateitem()) {
                ItemDTO createdItemDTO = itemPersistance.createItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(persistedCartDTO.getId(), createdItemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        // update item
        if (cart.getUpdateitem() != null) {
            for (ItemDTO itemDTO : cart.getUpdateitem()) {
                itemPersistance.updateItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(persistedCartDTO.getId(), itemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        return cart;
    }

    public CartMasterDTO getMasterCart(Long id) {
        return cartMasterPersistance.getCart(id);
    }

    public void deleteMasterCart(Long id) {
        cartPersistance.deleteCart(id);
    }

    public void updateMasterCart(CartMasterDTO cart) {
        cartPersistance.updateCart(cart.getCartEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (cart.getCreateitem() != null) {
            for (ItemDTO itemDTO : cart.getCreateitem()) {
                ItemDTO createdItemDTO = itemPersistance.createItem(itemDTO);
                CartitemEntity cartItemEntity = new CartitemEntity(cart.getCartEntity().getId(), createdItemDTO.getId());
                cartMasterPersistance.createCartitemEntity(cartItemEntity);
            }
        }
        // update item
        if (cart.getUpdateitem() != null) {
            for (ItemDTO itemDTO : cart.getUpdateitem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (cart.getDeleteitem() != null) {
            for (ItemDTO itemDTO : cart.getDeleteitem()) {
                cartMasterPersistance.deleteCartitemEntity(cart.getCartEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
