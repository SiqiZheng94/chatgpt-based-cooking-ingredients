package com.example.chatgptbasedcookingingredients;

import java.util.List;

public record ChatgptRequest(String model, List<ChatgptMessage> messages ) {
}
