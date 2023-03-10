package io.adarsh.ipldashboard.data;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import io.adarsh.ipldashboard.model.Match;
 
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setCity(matchInput.getCity());

    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());
    match.setDate(LocalDate.parse(matchInput.getDate()));

    String firstInningsTeam, secondInningsTeam;

    if("bat".equals(matchInput.getToss_decision())){
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    else{
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);

    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());
    match.setMatchWinner(matchInput.getWinner());

    return match;
  }
}