PlayerHubItems
Give your players a head that can perform a command, and a book.
PlayerHubItems V 3.0
When a player joins your server, they will receive their own head* that is customisable. For example, you can change the name, slot and lore, as well as the command it executes, the sound it plays, and whether you want it to not be place able. Player Hub Items now also includes a book which you can write in game, and give to every player on join. The book has support for color codes and a variable: %player% which will show the player's name.

Book Configuration
[CODE]BookConfig:

  # WithItemFlag / WithoutItemFlag
  # WithItemFlag = Displays the attributes.
  # WithoutItemFlag = Hides the author and attributes.
  Type: 'WithoutItemFlag'
  # Inventory position. (1-36)
  Position: 9
  # Change the uthor, title and lore of the book to your liking.
  # Variables:
  # {player} get name of the player.
  BookMeta:
    DisplayAuthor: '&dBy ElSheriff'
    DisplayTitle: '&aServer Rules'
    DisplayLore:
      - '&bThis is a test text.'
      - '&bModify it to your liking in the configuration.'
  Effects:
    - ''
  # If you do not want any sound, leave this option empty.
  Sounds:
    - 'ENTITY_CHICKEN_EGG'
  Disable:
    InteractBook: true
    PlaceBook: true
    DropBook: true
  GiveBook:
    OnPlayerJoin: true
    OnPlayerRespawn: true
  # Variables:
  # {player} get name of the player.
BookData:
  ==: org.bukkit.inventory.ItemStack
  type: WRITTEN_BOOK
  meta:
    ==: ItemMeta
    meta-type: BOOK_SIGNED
    title: Title
    author: Author
    pages:
    - "&7This book was written by himself &6md_5"
    generation: 0[/CODE]

Head Configuration
[CODE]HeadConfig:

  # WithItemFlag / WithoutItemFlag
  # WithItemFlag = Displays the attributes.
  # WithoutItemFlag = Hides the attributes.
  Type: 'WithItemFlag'
  # Inventory position. (1-36)
  Position: 1
  # Change the name and lore of the head to your liking.
  # Variables:
  # {player} get name of the player.
  ItemMeta:
    DisplayName: '&aHead of &6{player}'
    DisplayLore:
      - '&bThis is a test text.'
      - '&bModify it to your liking in the configuration.'
  HeadCommand:
    # If you want the console command is executed, or the player.
    ByConsole: true
    Command: 'me My name is {player}'
  # If you do not want any effect, leave this option empty.
  Effects:
    - ''
  # If you do not want any sound, leave this option empty.
  Sounds:
    - 'ENTITY_CHICKEN_EGG'
  Disable:
    InteractHead: true
    PlaceHead: true
    DropHead: true
  GiveHead:
    OnPlayerJoin: true
    OnPlayerRespawn: true[/CODE]

Messages Configuration
[CODE]MessagesConfig:

  ReloadConfig: '&aConfiguration files reloaded successfully!'
  GetHead: '&aYou have been given your head.'
  GetBook: '&aYou have been give the book'
  SaveBook: '&aThe book in your hand has been saved to &dbook.yml'
  SaveBookError: '&cError: &fThe material you have on hand, is not a book.'[/CODE]

Commands
playerhubitems
This is the main plugin command.
aliases: phi
Permissions
phi.book.join
He gives the player book when entering.
default: operator
phi.book.respawn
He gives the player book when respawn.
default: operator
phi.book.bypass
Bypass the restrictions of the book.
default: operator
phi.book.join
He gives the player head when entering.
default: operator
phi.book.respawn
He gives the player head when respawn.
default: operator
phi.book.bypass
Bypass the restrictions of the head.
default: operator
phi.command.book
Gives access to the command /phi book
default: operator
phi.command.head
Gives access to the command /phi head
default: operator
phi.command.reload
Gives access to the command /phi reload
default: operator
