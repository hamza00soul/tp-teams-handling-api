package fr.sorbonne.paris.nord.university.api.Service;

import fr.sorbonne.paris.nord.university.api.DTOs.TeamDto;
import fr.sorbonne.paris.nord.university.api.Entity.Team;
import fr.sorbonne.paris.nord.university.api.Mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper mapper;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTeamById() {
        // Create a mock team
        Team mockTeam = new Team(1L,"Team A", "slogan1");
        // Create mock repository and mapper
        TeamRepository mockRepository = Mockito.mock(TeamRepository.class);
        TeamMapper mockMapper = Mockito.mock(TeamMapper.class);
        // Inject the mocks into the service
        TeamService service = new TeamService(mockRepository, mockMapper);
        // Configure the repository mock to return the mock team when findById is called
        when(mockRepository.findById(1L)).thenReturn(Optional.of(mockTeam));
        // Configure the mapper mock to return a TeamDto when map is called
        when(mockMapper.map(mockTeam)).thenReturn(new TeamDto(1L, "Team A", "slogan1"));
        // Call the getTeamById method
        TeamDto result = service.getTeamById(1L);
        // Assert that the result is not null
        assertNotNull(result);
        // Assert that the result has the expected properties
        assertEquals(1L, result.getId().longValue());
        assertEquals("Team A", result.getName());
        assertEquals("slogan1", result.getSlogan());
    }

    @Test
    public void testGetAllTeams() {
        // Create a list of mock teams
        List<Team> mockTeams = Arrays.asList(
                new Team(1L, "Team A", "slogan1"),
                new Team(2L, "Team B", "slogan2"),
                new Team(3L, "Team C", "slogan3")
        );
        // Create mock repository and mapper
        TeamRepository mockRepository = Mockito.mock(TeamRepository.class);
        TeamMapper mockMapper = Mockito.mock(TeamMapper.class);
        // Inject the mocks into the service
        TeamService service = new TeamService(mockRepository, mockMapper);
        // Configure the repository mock to return the list of mock teams when findAll is called
        when(mockRepository.findAll()).thenReturn(mockTeams);
        // Configure the mapper mock to return a TeamDto when map is called
        when(mockMapper.map(mockTeams.get(0))).thenReturn(new TeamDto(1L, "Team A", "slogan1"));
        when(mockMapper.map(mockTeams.get(1))).thenReturn(new TeamDto(2L, "Team B", "slogan2"));
        when(mockMapper.map(mockTeams.get(2))).thenReturn(new TeamDto(3L, "Team C", "slogan3"));
        // Call the getAllTeams method
        List<TeamDto> result = service.getAllTeams();
        // Assert that the result is not null
        assertNotNull(result);
        // Assert that the result has the expected size
        assertEquals(3, result.size());
        // Assert that the result has the expected properties
        assertEquals(1L, result.get(0).getId().longValue());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("slogan1", result.get(0).getSlogan());
        assertEquals(2L, result.get(1).getId().longValue());
        assertEquals("Team B", result.get(1).getName());
        assertEquals("slogan2", result.get(1).getSlogan());
        assertEquals(3L, result.get(2).getId().longValue());
        assertEquals("Team C", result.get(2).getName());
        assertEquals("slogan3", result.get(2).getSlogan());
    }


    @Test
    public void testInsertTeam() {
        // Create a mock team
        Team mockTeam = new Team(null, "Team A", "slogan1");
        // Create a list of mock teams
        List<Team> mockTeams = Arrays.asList(
                mockTeam,
                new Team(2L, "Team B", "slogan2")
        );
        // Create mock repository and mapper
        TeamRepository mockRepository = Mockito.mock(TeamRepository.class);
        TeamMapper mockMapper = Mockito.mock(TeamMapper.class);
        // Inject the mocks into the service
        TeamService service = new TeamService(mockRepository, mockMapper);
        // Configure the repository mock to return the mock teams when findAll is called
        when(mockRepository.findAll()).thenReturn(mockTeams);
        // Configure the mapper mock to return a TeamDto when map is called
        when(mockMapper.map(mockTeams.get(0))).thenReturn(new TeamDto(1L, "Team A", "slogan1"));
        when(mockMapper.map(mockTeams.get(1))).thenReturn(new TeamDto(2L, "Team B", "slogan2"));
        // Call the insertTeam method
        List<TeamDto> result = service.insertTeam(mockTeam);
        // Assert that the result is not null
        assertNotNull(result);
        // Assert that the result has the expected size
        assertEquals(2, result.size());
        // Assert that the repository's save method was called with the mock team
        verify(mockRepository).save(mockTeam);
        // Assert that the first result has the expected properties
        assertEquals(1L, result.get(0).getId().longValue());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("slogan1", result.get(0).getSlogan());
        // Assert that the second result has the expected properties
        assertEquals(2L, result.get(1).getId().longValue());
        assertEquals("Team B", result.get(1).getName());
        assertEquals("slogan2", result.get(1).getSlogan());
    }


    @Test
    public void testUpdateTeamById() {
        // Create a mock team
        Team mockTeam = new Team(1L, "Team A", "slogan1");
        // Create a mock repository and mapper
        TeamRepository mockRepository = Mockito.mock(TeamRepository.class);
        TeamMapper mockMapper = Mockito.mock(TeamMapper.class);
        // Inject the mocks into the service
        TeamService service = new TeamService(mockRepository, mockMapper);
        // Configure the repository mock to return the mock team when findById is called
        when(mockRepository.findById(1L)).thenReturn(Optional.of(mockTeam));
        // Configure the mapper mock to return a TeamDto when map is called
        when(mockMapper.map(mockTeam)).thenReturn(new TeamDto(1L, "Team A", "slogan1"));

        // Call the updateTeamById method
        TeamDto result = service.updateTeamById(1L, new Team(1L, "Team A", "slogan2"));

        // Assert that the result is not null
        assertNotNull(result);
        // Assert that the result has the expected properties
        assertEquals(1L, result.getId().longValue());
        assertEquals("Team A", result.getName());
        assertEquals("slogan2", result.getSlogan());
        // Verify that the repository's save method is called with the updated team
        verify(mockRepository).save(mockTeam);
    }


    @Test
    public void testDeleteTeamById() {
        // Create a list of mock teams
        Team mockTeam1 = new Team(1L, "Team A", "slogan1");
        Team mockTeam2 = new Team(2L, "Team B", "slogan2");
        List<Team> mockTeams = Arrays.asList(mockTeam1, mockTeam2);

        // Create mock repository and mapper
        TeamRepository mockRepository = Mockito.mock(TeamRepository.class);
        TeamMapper mockMapper = Mockito.mock(TeamMapper.class);
        // Inject the mocks into the service
        TeamService service = new TeamService(mockRepository, mockMapper);

        // Configure the repository mock to return the mock team when deleteById is called
        when(mockRepository.findAll()).thenReturn(mockTeams);

        // Configure the mapper mock to return a TeamDto when map is called
        when(mockMapper.map(mockTeams.get(0))).thenReturn(new TeamDto(1L, "Team A", "slogan1"));
        when(mockMapper.map(mockTeams.get(1))).thenReturn(new TeamDto(2L, "Team B", "slogan2"));

        // Call the deleteTeamById method
        List<TeamDto> result = service.deleteTeamById(2L);

        List<TeamDto> expected = Arrays.asList(new TeamDto(1L, "Team A", "slogan1"));

        // Assert that the result is not null
        assertNotNull(result);
        // Assert that the result has the expected size
        assertEquals(1, result.size());
        // Assert that the result has the expected properties
        assertEquals(expected, result);
    }


}
