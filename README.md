# poker
Задание:

Напишите класс Java для «Руки» в игре в покер.

Значения карт приведены в виде списка:

ЗНАЧЕНИЯ = ['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' , 'A']

Где T = 10, J = «Валет», Q = «Дама», K = «Король» и A - «Туз»

Возможные «масти»

МАСТИ = ['C', 'D', 'H', 'S']

Где C = Clubs (Крести), D = Diamonds(Бубны), ??H = Hearts(Черви) и S = Spades (Пики).

Рука содержит 5 карт и должна быть неизменной и инициализируемой из строки, например:

three_of_a_kind = Hand.fromString ('4D 4C 4H 7H 8D')

one_pair = Hand.fromString ('4D 3D 3D 7H AD')

убедитесь, что рука инициализируется только так.

Рука должна иметь возможность определять свою «ценность» в соответствии с правилами покера.

Возможные значения задаются как (по порядку, от низкого до высокого значения):

VALUES = [HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,

FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH]

Рука должна реализовать метод toString (), который дает строковое представление

Пример:

<hand [TS, JS, QS, KS, AS], 'Royal Flush'>

<hand [5S, 6S, 7S, 8S, 9S], 'Straight Flush'>

<hand [7S, TC, TH, TS, TD], 'Four of a Kind'>

<hand [5H, 5C, QD, QC, QS], 'Full House'>

<hand [2D, 3D, 7D, QD, AD], 'Flush'>

<hand [4D, 5D, 6D, 7H, 8D], 'Straight'>

Вы должны быть в состоянии отсортировать список рук и сравнить руки в соответствии со значениями в VALUES.
