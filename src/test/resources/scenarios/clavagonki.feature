Feature: Bot for the clavagon website
  Background: I'm on the main page of the site
    Given Open webSite "https://klavogonki.ru/go?type=normal"

    Scenario: The bot starts the game and enters the words itself
      When Start game
      And Waiting start game
      And Enter the highlighted word in a loop
      Then Fix that game is over symbol more 1500