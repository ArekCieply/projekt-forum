/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.PrivateMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PrivateMessageRepository;
import mappers.PrivateMessageMapper;
import entities.PrivateMessage;
import lombok.RequiredArgsConstructor;
import repositories.PrivateConversationRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class PrivateMessageService {

    final private PrivateMessageRepository privateMessageRepository;
    final CheckUserId check;
    final PrivateMessageMapper privateMessageMapper;
    final UserRepository userRepository;
    final private PrivateConversationRepository privateConversationRepository;

    public void addMessage(PrivateMessageDto privateMessageDto, String auth) {
        if (check.checkId(auth, privateMessageDto.userId())) {
            PrivateMessage privateMessage = privateMessageMapper.dtoToEntity(privateMessageDto);
            privateMessage.setUser(userRepository.findFirstByIdEquals(privateMessageDto.userId()));
            privateMessage.setPrivateConversation(privateConversationRepository.findFirstByIdEquals(privateMessageDto.privateConversationId()));
            privateMessageRepository.save(privateMessage);
        }
    }

    public Iterable<PrivateMessage> getMessages(Integer id) {
        return privateMessageRepository.findByPrivateConversationIdEquals(id);

    }

}
