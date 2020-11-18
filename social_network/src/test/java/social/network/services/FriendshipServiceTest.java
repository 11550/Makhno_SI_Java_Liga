package social.network.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import social.network.domain.Friendship;
import social.network.domain.User;
import social.network.dto.UserEditDto;
import social.network.repository.FriendshipRepository;
import social.network.service.FriendshipService;
import social.network.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class FriendshipServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private FriendshipRepository friendshipRepository;

    private FriendshipService friendshipService;

    private User user;
    private User friend;
    private Friendship friendship1;
    private Friendship friendship2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        friendshipService = new FriendshipService(userService, friendshipRepository);

        user = new User("test@test.test", "Ivan", "Ivanov");
        friend = new User("testl@testl.test", "Stepan", "Stepanov");
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        user.setId(id1);
        friend.setId(id2);
        friendship1 = new Friendship(user, friend);
        friendship1.setId(1l);
        friendship2 = new Friendship(friend, user);
        friendship2.setId(2l);
    }

    @Test
    @DisplayName("добавление друга Ок")
    public void testAddFriendOk() {
        Mockito.when(friendshipRepository.findByUserAndFriend(any(), any())).thenReturn(Optional.empty());
        Mockito.when(friendshipRepository.save(any())).thenReturn(friendship2);
        Mockito.when(friendshipRepository.save(any())).thenReturn(friendship1);
        Long actualId = friendshipService.add(user.getId(), friend.getId());
        Mockito.verify(friendshipRepository, Mockito.times(1)).findByUserAndFriend(any(), any());
        Mockito.verify(friendshipRepository, Mockito.times(2)).save(any());
    }

    @Test
    @DisplayName("удаление друга Ок")
    public void testDeleteFriendOk() {
        friendshipService.delete(user.getId(), friend.getId());
        assertEquals(Optional.empty(), friendshipRepository.findByUserAndFriend(user, friend));
        Mockito.verify(friendshipRepository, Mockito.times(2)).deleteByUserAndFriend(any(), any());
    }

    @Test
    @DisplayName("получение списка друзей Ок")
    public void testGetFriendListOk() {
        List<User> friends = new ArrayList<>();
        friends.add(friend);
        Mockito.when(friendshipRepository.findFriendsByUser(any())).thenReturn(friends);
        List<UserEditDto> expectedFriends = new ArrayList<>();
        friends.forEach(friend -> expectedFriends.add(userService.convertToEditDto(friend)));
        List<UserEditDto> actualFriends = friendshipService.findAll(user.getId());
        assertEquals(expectedFriends, actualFriends);
        Mockito.verify(friendshipRepository, Mockito.times(1)).findFriendsByUser(any());
    }
}
