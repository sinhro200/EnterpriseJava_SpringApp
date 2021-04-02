# EnterpriseJava_SpringApp
14.Менеджмент курсов иностранного языка
Курсы иностранного языка предлагают обучение нескольким иностранным языкам (английскому, французскому, немецкому, японскому и др.),
причем на нескольких уровнях (начальном, среднем, продвинутом) и с разной степенью интенсивности (интенсив, обычное и поддерживающее обучение). 
На курсах учатся K слушателей (15≤ K ≤ 30), каждый слушатель может быть записан на обучение нескольким языкам,
причем для каждого языка у него может быть свой уровень и интенсивность.
Занятия могут быть индивидуальные и групповые, причем в группу записываются слушатели одного языка, уровня и интенсивности. 
В группе не может быть менее 5 и более 10 человек (оптимально – 7 человек). 
Длительность курса для групповых занятий может варьироваться от двух недель до трех месяцев (для индивидуальных занятий подобных ограничений нет). 
Количество занятий в неделю (от 1 до 5 раз) и длительность курса зависит от его степени интенсивности.
Оплата курсов осуществляется авансом за две очередные недели обучения, стоимость курсов различается для разных языков, 
  а стоимость индивидуального обучения выше группового.
Требуется создать компьютерную систему, автоматизирующую управление деятельностью курсов. 
Создаваемая система хранит информацию о преподаваемых языках, группах, слушателях, их посещаемости и оплате. 
В случае, когда некоторый слушатель прекращает посещать и оплачивать курсы, он исключается из соответствующей группы.
В начале очередного двухнедельного периода обучения система рассматривает поступившие заявки на обучение от новых и старых слушателей. 
В заявке указывается фамилия слушателя, язык, уровень и интенсивность обучения. 
В зависимости от поступивших заявок и численности уже существующих групп, система организует новые группы,
дополняет старые группы новыми слушателями и/или переформирует старые группы, объединяя в одну несколько групп – 
  так что численность групп остается близкой к оптимальной. 
В случае невозможности подобрать группу для нового слушателя система предлагает ему индивидуальные занятия, сохраняя тем не менее его заявку – 
  с тем, чтобы через две недели вновь попробовать подобрать ему подходящую группу. 
По окончании установленного курса его слушатели автоматически переводятся на следующий уровень изучения языка 
(если они оплачивают очередной двухнедельный период и не подают другой заявки).
Необходимо испытать построенную систему менеджмента языковых курсов, задав некоторое их начальное состояние (языки изучения, слушатели, группы) 
и смоделировав поток заявок на обучение от новых слушателей. 
Цель моделирования – сбор статистики для анализа работы курсов, период моделирования – М месяцев (3≤ М ≤ 12), шаг моделирования – две недели.
Поток поступающих заявок на обучение следует моделировать статистически: случайными величинами являются количество заявок на очередном шаге моделирования 
  и все составляющие каждой заявки: нужный иностранный язык, его уровень, интенсивность обучения. 
Прекращение обучения слушателей на курсах (в том числе досрочное) также следует моделировать как случайное событие, происходящее с определенной вероятностью.
В ходе моделирования должна быть возможность просмотреть информацию о текущей работе языковых курсов: о слушателях, группах, 
их численности и расписании, стоимости курсов и произведенной оплате и т.д. 
По окончании моделирования следует вывести статистику их работы в течение всего периода моделирования, 
  в том числе – число слушателей каждого языка и уровня, число групп, средняя их численность и т.п.
