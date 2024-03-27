package org.example.lab11.Service;
import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Category;
import org.example.lab11.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
        private final CategoryRepository categoryRepository;

        public List<Category> getAllCategories(){
            return categoryRepository.findAll();
        }
        public void addCategory(Category category){
            categoryRepository.save(category);
        }

        public void updateCategory(Integer id,Category category){
            Category c=categoryRepository.findCategoryById(id);
            if (c==null){
                throw new ApiException("not found id");
            }
            c.setName(category.getName());
            categoryRepository.save(c);
        }

        public void deleteCategory(Integer id){
            Category c=categoryRepository.findCategoryById(id);
            if (c==null){
                throw new ApiException("not found id");
            }
            categoryRepository.delete(c);
        }

//---------------- 1\search category by name
        public Category searchByName(String name){
            Category c=categoryRepository.findCategoryByName(name);
            if (c==null){
                throw new ApiException("not found result");
            }
            return c;
        }
    }


