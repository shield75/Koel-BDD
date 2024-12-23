Feature: Search Song

  Scenario Outline: Searching with existing song
    Given The user is logged into the koel app
    And The user clicks on All Songs from the left side ber
    And The user clicks on search field and User inputs a song name "<searchSong>" in the search field
    Then The searched song "<searchSong>" results if matched should be populated on the Search results page


    Examples:
      | searchSong      |
      | If I Find A Way |

  Scenario Outline: Searching with non existing song
    Given The user is logged into the koel app
    And The user clicks on All Songs from the left side ber
    And The user clicks on search field and User inputs a song name "<searchSong>" in the search field
    Then The searched song "<searchSong>" results if not matched it should show no results in every section


    Examples:
      | searchSong |
      | abdc       |


  Scenario Outline: Ignore white space in trailing\heading while searching a song and search text should be case sensitive
    Given The user is logged into the koel app
    And The user clicks on All Songs from the left side ber
    And The user clicks on search field and User inputs a song name "<searchSong>" in the search field
    Then The searched song "<searchSong>" results should remove the whitespace in trailing/heading also case sensitive


    Examples:
      | searchSong|
      |Episode 2|
      |    Episode 2|
      |Episode 2     |
      |   Episode 2   |
      | EPISODE 2   |
      | EpIsOdE 2   |
      | episode 2   |

  Scenario Outline: Searching with existing song and clear query with close button
    Given The user is logged into the koel app
    And The user clicks on All Songs from the left side ber
    And The user clicks on search field and User inputs a song name "<searchSong>" in the search field
    Then Clear the search field using close button

    Examples:
      | searchSong|
      | If I Find A Way |


