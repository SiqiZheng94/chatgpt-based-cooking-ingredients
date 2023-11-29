package com.example.chatgptbasedcookingingredients;

import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    @Value("${OPEN_API_KEY}")
    private String chatGPTApiKey;
    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {
        ChatgptRequest chatgptRequest = new ChatgptRequest("gpt-3.5-turbo",
                List.of(new ChatgptMessage("user", "answer in just one word:is "+ingredient+" vegan,vegetarian or regular")));
        ChatgptResponse response = Objects.requireNonNull(
                WebClient.create()
                        .post()
                        .uri("https://api.openai.com/v1/chat/completions")
                        .bodyValue(chatgptRequest)
                        .header("Authorization", "Bearer "+chatGPTApiKey)
                        .retrieve()
                        .toEntity(ChatgptResponse.class)
                        .block()
        ).getBody();
        if(response.choices().isEmpty()){
            return "nothing found!";
        }
        return response.choices().get(0).message().content();

    }

}
