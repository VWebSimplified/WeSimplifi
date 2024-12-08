package com.Wesimplifi.WeSimplifiFirstProject.service;

import com.Wesimplifi.WeSimplifiFirstProject.entity.User;
import com.Wesimplifi.WeSimplifiFirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User user;

    public Map<String, Object> userRegister(User user) {

        Map<String, Object> response = new HashMap<>();

        Optional<User> existingUser = userRepository.findById(user.getEmail());
        if (existingUser.isPresent()) {
            User existingUserDetails = existingUser.get();
            response.put("Hey : ", "You are already registered, Please check below credentials");
            response.put(" Your Email", existingUserDetails.getEmail());
            response.put(" Your promoCode against your Email : ", existingUserDetails.getPromoCode());

            return response;
        }

        // new User without PromoCode
        if (user.getPromoCode() == null) {
            user.setPromoCode(UUID.randomUUID().toString());
            userRepository.save(user);
            response.put("Congrats you are successfully Signed in We Simplifi Portal", "Your Credentials are below");
            response.put("Email : ", user.getEmail());
            response.put("Name: ", user.getName());
            response.put("City : ", user.getCity());
            response.put("PromoCode :", user.getPromoCode());
            response.put("Date of Birth :", user.getDateOfBirth());

            return response;
        }

        // New User with promoCode- Vaibhav- Old user, Aditya new user- Using vaibhav's PromoCode to sign up
        if (user.getPromoCode() != null) {
            Optional<User> originalUser = userRepository.findByPromoCode(user.getPromoCode());
            if (originalUser.isPresent()) {
                User referringUser = originalUser.get();
                // Will increase reference Counter value of Vaibhav and will save him again
                int updatedReferenceCounterValue = referringUser.increaseCounterValueByOne();
                referringUser.setReferenceCounter(updatedReferenceCounterValue);
                userRepository.save(referringUser);

                // setting referBy column of Aditya's row in database
                user.setReferBy(referringUser.getId());

                // Saving Aditya's details
                user.setPromoCode(UUID.randomUUID().toString());
                userRepository.save(user);

                // fetching Aditya's details
                response.put("Congrats you are successfully Signed in We Simplifi Portal Using a PromoCode", "Your Credentials are below");
                response.put("Your Email : ", user.getEmail());
                response.put("Your Name: ", user.getName());
                response.put("City : ", user.getCity());
                response.put("Your PromoCode is : ", user.getPromoCode());
                response.put("Your Date of Birth is : ", user.getDateOfBirth());
                response.put("Name by who you got referred : ", referringUser.getName());
                response.put("Promocode of referring user : ", referringUser.getPromoCode());
                return response;
            }

            response.put("Alert", "You are using invalid promoCode");
        }
        return response;
    }
}
