package com.bfb.tennis.application;

import com.bfb.tennis.adapters.out.InMemoryScoreRepository;
import com.bfb.tennis.domain.model.TennisPlayer;
import com.bfb.tennis.domain.ports.in.GameService;
import com.bfb.tennis.domain.ports.out.ScoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("unit tests on GameServiceImplTest to test GameServiceImpl")
class GameServiceImplTest {

    private GameService gameService;
    private ScoreRepository scoreRepository;
    private TennisPlayer playerA;
    private TennisPlayer playerB;

    @BeforeEach
    void setUp() {
        playerA = new TennisPlayer('A');
        playerB = new TennisPlayer('B');
        scoreRepository = new InMemoryScoreRepository();
        gameService = new GameServiceImpl(scoreRepository);
    }

    @AfterEach
    void tearDown() {

    }

    @ParameterizedTest(name = "input={0} | expectedErrorMessage={1}")
    @MethodSource(value="invalidArguments")
    @DisplayName("should Get IllegalArgumentException When Input Is Not Valid")
    void shouldGetIllegalArgumentExceptionWhenInputIsNotValid(final Character input, final String expectedErrorMessage){
        gameService.startNewGame(playerA, playerB);
        assertThatThrownBy(() -> gameService.pointWonBy(input))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage(expectedErrorMessage);

    }

    @ParameterizedTest(name = "input={0} | expectedMessageScore={1} | expectedWinnerName={2}")
    @MethodSource(value="validArguments")
    @DisplayName("should print a valid score as expected")
    void shouldPrintAValidScoreAsExpected(final String input, final String expectedMessageScore, final String expectedWinnerName ) {
        gameService.startNewGame(playerA, playerB);
        Character[] winnersOfPoint = input.chars().mapToObj((c -> (char) c)).toArray(Character[]::new);
        Stream<Character> streamWinnersOfPoint = Arrays.stream(winnersOfPoint);
        streamWinnersOfPoint.forEach( winnerOfPoint ->  gameService.pointWonBy(winnerOfPoint));
        String winnerName = gameService.getWinnerName();
        String latestScore = scoreRepository.getLatestScore();
        assertThat(latestScore).isEqualTo(expectedMessageScore);
        assertThat(winnerName).isEqualTo(expectedWinnerName);
    }

    @Test
    @DisplayName("should Print No Winner When No Win Point Yet As Expected")
    void ShouldPrintNoWinnerWhenNoWinPointYetAsExpected() {
        String expectedWinnerName = "Pas de gagnant";
        gameService.startNewGame(playerA, playerB);
        String winnerName = gameService.getWinnerName();
        assertThat(winnerName).isEqualTo(expectedWinnerName);
    }

    static Stream<Arguments> validArguments(){
        var args = new LinkedList<>(List.of(
                arguments("ABABAA", """
                        Player A : 15 / Player B : 0
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 40 / Player B : 30
                        Player A wins
                        ""","A"),
                arguments("ABABBB", """
                        Player A : 15 / Player B : 0
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 30 / Player B : 40
                        Player B wins
                        ""","B"),
               arguments("ABABBAAA", """
                        Player A : 15 / Player B : 0
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 30 / Player B : 40
                        Player A : 40 / Player B : 40
                        Player A advantage
                        Player A wins
                        """, "A"),
                arguments("ABABBAABAA", """
                        Player A : 15 / Player B : 0
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 30 / Player B : 40
                        Player A : 40 / Player B : 40
                        Player A advantage
                        Player A : 40 / Player B : 40
                        Player A advantage
                        Player A wins
                        ""","A"),
                arguments("ABABBABB", """
                        Player A : 15 / Player B : 0
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 30 / Player B : 40
                        Player A : 40 / Player B : 40
                        Player B advantage
                        Player B wins
                        """, "B"),
                arguments("BAABABBB", """
                        Player A : 0 / Player B : 15
                        Player A : 15 / Player B : 15
                        Player A : 30 / Player B : 15
                        Player A : 30 / Player B : 30
                        Player A : 40 / Player B : 30
                        Player A : 40 / Player B : 40
                        Player B advantage
                        Player B wins
                        ""","B"),
                arguments("BABABABABB", """
                        Player A : 0 / Player B : 15
                        Player A : 15 / Player B : 15
                        Player A : 15 / Player B : 30
                        Player A : 30 / Player B : 30
                        Player A : 30 / Player B : 40
                        Player A : 40 / Player B : 40
                        Player B advantage
                        Player A : 40 / Player B : 40
                        Player B advantage
                        Player B wins
                        ""","B")
        ));
        return args.stream();
    }

    static Stream<Arguments> invalidArguments() {
        var args = new LinkedList<>(List.of(
                arguments(null, "input is null"),
                arguments("C", "input is not valid"))
        );
        return args.stream();
    }
}