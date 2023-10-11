package com.matzip.service;


import com.matzip.dto.UsersFormDto;
import com.matzip.entity.Users;
import com.matzip.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


@Service
@Transactional
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    @Value("${userImgLocation}")
    private String userImgLocation;
    private final UsersRepository usersRepository;
    private final FileService fileService;


    public Users saveUsers(Users users, MultipartFile userImgFile) throws Exception {
        String oriImgName = userImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(userImgLocation, oriImgName, userImgFile.getBytes());
            imgUrl = "/images/users/" + imgName;
        }

        //상품 이미지 정보 저장
        users.setUser_image(imgUrl);

        validateDuplicateUsers(users);
        return usersRepository.save(users);
    }

    public void updateUsers(UsersFormDto usersFormDto, MultipartFile userImgFile) throws Exception {
        String oriImgName = userImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //객체 찾기
        Users users = usersRepository.findByUserid(usersFormDto.getUserid());

        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            //사진이 바뀐 경우
            imgName = fileService.uploadFile(userImgLocation, oriImgName, userImgFile.getBytes());
            imgUrl = "/images/users/" + imgName;
            usersFormDto.setUser_image(imgUrl);
        }else{
            //사진이 바뀌지 않은 경우(기존 이미지 저장)
            usersFormDto.setUser_image(users.getUser_image());
        }

        //usersFormDto(수정폼 입력 정보)로 data변경
        users.updateUsers(usersFormDto);
        usersRepository.save(users);
    }


    private void validateDuplicateUsers(Users users) {
        Users findUsers = usersRepository.findByUserid(users.getUserid());
        if (findUsers != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        Users users = usersRepository.findByUserid(userid);

        if(users == null){
            throw new UsernameNotFoundException(userid);
        }

        return User.builder()
                .username(users.getUserid())
                .password(users.getUser_pwd())
                .roles(users.getUser_role().toString())
                .build();
    }


}
