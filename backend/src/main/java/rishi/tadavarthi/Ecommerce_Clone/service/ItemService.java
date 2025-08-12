package rishi.tadavarthi.Ecommerce_Clone.service;

import org.springframework.web.multipart.MultipartFile;
import rishi.tadavarthi.Ecommerce_Clone.io.ItemRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.ItemResponse;

import java.util.List;

public interface ItemService {

    ItemResponse add (ItemRequest request, MultipartFile file);
    List<ItemResponse> fetchItems();

    void deleteItem (String itemId);


}
