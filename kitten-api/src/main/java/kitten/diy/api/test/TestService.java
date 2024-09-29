package kitten.diy.api.test;

import kitten.core.coredomain.file.entity.ImageFile;
import kitten.core.coredomain.file.repository.ImageFileRepository;
import kitten.core.coredomain.item.entity.Item;
import kitten.core.coredomain.item.repository.ItemRepository;
import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.moru.entity.Moru;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruRepository;
import kitten.core.coredomain.parts.repository.PartsRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UsersRepository usersRepository;
    private final MoruPartsRepository moruPartsRepository;
    private final PartsRepository partsRepository;
    private final MoruRepository moruRepository;
    private final ImageFileRepository imageFileRepository;
    private final ItemRepository itemRepository;

    public void test() {
        Users users = Users.builder()
                .authRoles(AuthRoles.USER)
                .email("testetestsetstst@gmail.com")
                .build();

        users.changeCreateBy("kikiUsers");
        users.changeUpdateBy("kikiUsers");

        Users save = usersRepository.save(users);

        System.out.println(save);
    }

    public void testSaveMoru() {

//        ImageFile imageFile = ImageFile.builder()
//                .imageId("asdfsdaf")
//                .imageUrl("asdfsdaf")
//                .build();
//
//        imageFileRepository.save(imageFile);

//        Item item = itemRepository.findByKey(1L).get();
//
//        Moru moru = Moru.builder()
//                .name("모루인형2")
//                .build();
//        moruRepository.save(moru);
//
        MoruParts moruParts = MoruParts.builder()
                .name("테스트모루저장")
                .build();
        moruPartsRepository.save(moruParts);
    }
}
