package pro.angelogomez.ecommerce.backend.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import pro.angelogomez.ecommerce.backend.domain.model.Product;
import pro.angelogomez.ecommerce.backend.domain.port.IProductRepository;

import java.io.IOException;

@Slf4j
public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFile uploadFile;

    public ProductService(IProductRepository iProductRepository, UploadFile uploadFile) {
        this.iProductRepository = iProductRepository;
        this.uploadFile = uploadFile;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        if(product.getId()!=0){
            if (multipartFile == null){
                product.setUrlImage(product.getUrlImage());
            } else {
                String nameFile = product.getUrlImage().substring(product.getUrlImage().lastIndexOf("/") + 1);
                log.info("Este es el nombre de la imagen: {}", nameFile);
                if (!nameFile.equals("default.jpg")){
                    uploadFile.delete(nameFile);
                }
                product.setUrlImage(uploadFile.upload(multipartFile));
            }
        } else {
            product.setUrlImage(uploadFile.upload(multipartFile));
        }

        return iProductRepository.save(product);
    }
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }
    public Product findById(Integer id) {
        return iProductRepository.findById(id);
    }
    public void deleteById(Integer id) {
        Product product = findById(id);
        String nameFile = product.getUrlImage().substring(product.getUrlImage().lastIndexOf("/") + 1);
        log.info("Este es el nombre de la imagen: {}", nameFile);
        if (!nameFile.equals("default.jpg")){
            uploadFile.delete(nameFile);
        }
        iProductRepository.deleteById(id);
    }
}
