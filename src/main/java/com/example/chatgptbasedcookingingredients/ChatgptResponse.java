package com.example.chatgptbasedcookingingredients;

import java.awt.*;
import java.util.List;
public record ChatgptResponse(List<ChatgptChoice> choices) {
}
