Feature: Favorites
  Scenario Outline: If no songs are marked as a favorite, the playlist page should be empty
    Given The user click on All songs
    And The user click on favorites under playlist
    Then The user will show "<message>" message.

    Examples:
     | message|
     | No favorites yet.\nClick the icon to mark a song as favorite.|

  Scenario: Favorites playlist page should contain all songs saved as a favorite by the user
    Given The user click on All songs
    When The user should add song to favorite
    Then The user will show the added song to favorite

  Scenario: User should be able to download songs from the Favorites playlist page
    Given The user click on All songs
    When The user should go to favorite Page and download the first song
    Then the first song "M33 Project - Emotional Soundtrack.mp3" will be downloaded



  Scenario Outline: User should be able to delete songs from the Favorites playlist page
    Given The user click on All songs
    When The user should delete song from favorite
    Then The user will show "<message>" message.

    Examples:
      | message|
      | No favorites yet.\nClick the icon to mark a song as favorite.|


