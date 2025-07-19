/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import mappers.PostMapper;
import dto.PrivateConversationDto;
import mappers.PrivateConversationMapper;
import entities.PrivateConversation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PrivateConversationRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class PrivateConversationService {

    final private PrivateConversationRepository privateConversationRepository;
    final CheckUserId check;
    final PrivateConversationMapper privateConversationMapper;
    final UserRepository userRepository;

    public PrivateConversation addConversation(PrivateConversationDto ConversationDto, String auth) {
        int convId = 0;
        PrivateConversation conversationRet = null;
        if (check.checkId(auth, ConversationDto.user1Id())) {
            int uId1 = ConversationDto.user1Id();
            int uId2 = ConversationDto.user2Id();

            if (privateConversationRepository.findByUser1IdEqualsAndUser2IdEqualsOrUser1IdEqualsAndUser2IdEquals(uId1, uId2, uId2, uId1) == null) {
                PrivateConversation privateConv = privateConversationMapper.dtoToEntity(ConversationDto);
                privateConv.setUser1(userRepository.findFirstByIdEquals(uId1));
                privateConv.setUser2(userRepository.findFirstByIdEquals(uId2));
                privateConversationRepository.save(privateConv);
                PrivateConversation conversation = privateConversationRepository.findByUser1IdEqualsAndUser2IdEqualsOrUser1IdEqualsAndUser2IdEquals(uId1, uId2, uId2, uId1);
                convId = conversation.getId();
            } else {
                PrivateConversation conversation = privateConversationRepository.findByUser1IdEqualsAndUser2IdEqualsOrUser1IdEqualsAndUser2IdEquals(uId1, uId2, uId2, uId1);
                convId = conversation.getId();
                conversationRet = conversation;
            }
        }
        return conversationRet;
    }

    public Iterable<PrivateConversation> getConversations(Integer id, String auth) {
        if (check.checkId(auth, id)) {
            return privateConversationRepository.findByUser1IdEqualsOrUser2IdEquals(id, id);
        }
        return null;
    }
}
