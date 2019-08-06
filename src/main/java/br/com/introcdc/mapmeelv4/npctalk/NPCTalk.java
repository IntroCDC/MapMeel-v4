package br.com.introcdc.mapmeelv4.npctalk;
/*
 * Written by IntroCDC, Bruno Co�lho at 26/04/2019 - 05:42
 */

import br.com.introcdc.mapmeelv4.MapMain;
import br.com.introcdc.mapmeelv4.music.MapSound;
import br.com.introcdc.mapmeelv4.utils.MapUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPCTalk {

    public static List<NPCTalk> allNpcs = new ArrayList<>();

    private String name;
    private Location location;
    private List<String> stringList;
    private NPC npc;

    public NPCTalk(String name, Location location, List<String> stringList) {
        this.name = name;
        this.location = location;
        this.stringList = stringList;
        this.npc = findOrCreateNpc();
        allNpcs.add(this);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public NPC getNpc() {
        return npc;
    }

    public void talk(Player player) {
        getNpc().faceLocation(player.getLocation());

        NPC npc = MapUtils.createNPC(EntityType.PLAYER, player.getName(), player.getLocation());

        Location location = MapUtils.getLocation("world", getLocation().getX() + 3, getLocation().getY() + 3, getLocation().getZ() + 3, getLocation());

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            otherPlayer.setGameMode(GameMode.SPECTATOR);
            otherPlayer.teleport(location);
            otherPlayer.setFlySpeed(0);
            MapUtils.playSound(otherPlayer, MapSound.EFFECT_MESSAGE);
        }

        int current = 0;
        for (String message : getStringList()) {
            int c = current;
            Bukkit.getScheduler().runTaskLater(MapMain.getPlugin(), () -> {

                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                    otherPlayer.teleport(location);
                    otherPlayer.sendMessage("�b�o�l" + getName() + "�f: " + message);
                }

                if (c >= (getStringList().size() - 1)) {

                    for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                        otherPlayer.setFlySpeed(0.1f);
                        otherPlayer.setGameMode(GameMode.ADVENTURE);
                        otherPlayer.teleport(npc.getStoredLocation());
                    }

                    npc.destroy();
                    postTalk();
                }

            }, current * 75);
            current++;
        }
    }

    public void postTalk() {

    }

    public NPC findOrCreateNpc() {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.isSpawned() && npc.getName().contains(getName()) && npc.getStoredLocation().distance(getLocation()) < 10) {
                return npc;
            }
        }
        return MapUtils.createNPC(EntityType.PLAYER, name, location);
    }

    public static void loadNpcs() {
        new NPCTalk("BrunoCoelho", new Location(Bukkit.getWorld("world"), -330, 120, -178, -230, 1), Arrays.asList(
                "Ah, oi!", "Parece que voc� terminou o mapa n�o � mesmo?", "Enfim, primeiro...", "Parab�ns!", "Segundo...", "Bem, este � o fim do mapa",
                "O objetivo era mesmo as 120 estrelas", "no caso", "120 meels", "(piad�o)", "ent�o n�o possui mais nenhum objetivo dispon�vel para voc� jogar",
                "Por que eu estava aqui?", "N�o sei exatamente...", "Eu gosto de ficar aqui no meu apartamento...",
                "Apenas adimirando a vista", "Desde quando a hist�ria do MapMeel acabou", "N�o sei...",
                "Eu estive meio parado...", "parece que o Intro morreu junto com a hist�ria",
                "Aquele antigo Intro apaixonado simplesmente morreu", "com v�rios traumas e decep��es",
                "Ent�o por isso que me demonstro como Bruno agora", "pois o Intro morreu",
                "e agora quem voltou foi o Bruno, Bruno Co�lho"
                , "Mas enfim", "Voc�!", "Voc� � um guerreiro!",
                "Por ter passado na ra�a todo o MapMeel v4!", "Pra falar a verdade...", "Eu nunca esperava que aqueles port�es fossem ser abertos!",
                "Eu n�o sei", "Eu acho que ningu�m iria ter paci�ncia de chegar at� aqui", "Mas parece que voc� foi diferente",
                "Muito obrigado por compreender meu trabalho", "principalmente neste mapa",
                "Que basicamente n�o tem objetivo nenhum", "J� que a Meel nunca terminou o MapMeel v3",
                "Qual seria a chance dela querer terminar o MapMeel v4 kk", "Sabe qual � o pior?",
                "� que eu j� pensei em fazer um MapMeel v5", "Mas... o que eu faria agora?",
                "MapMeel v1 � um carrinho", "MapMeel v2 e v3 � parkour", "e esse � uma jun��o de Super Mario 64",
                "Com Gex - Get The Encko", "N�o sei", "Eu j� cheguei a ter ideia pro MapMeel v5", "Mas n�o tem hist�ria para contar",
                "Pois o v1 � contando 2014", "v2 contando 2015", "v3 contando 2016", "e esse na teoria seria 2017",
                "mas a hist�ria mesmo termina no in�cio de 2017", "e os �ltimos momentos j� foram mostrados no MapMeel v3",
                "Se eu fosse fazer um MapMeel v5", "seria no total foda-se, caguei 3 vezes", "sabe qual � o pior de novo?",
                "Eu j� cheguei a tentar come�ar kkkk", "Tem um servidor na m�quina", "mas s� tem um lobby inacabado",
                "Eu acho...", "que eu n�o vou continuar ele kk", "se for pra fazer algum MapMeel",
                "Vai ser do v4 para baixo", "n�o o v5 kk", "Mas enfim...",
                "Ent�o...",
                "Sim!", "Obrigado por jogar no MapMeel v4!", "Parab�ns, voc� finalizou o MapMeel v4 com sucesso!", ":)", ";)", ":D", ";D", "."
        )) {

            @Override
            public void postTalk() {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.sendTitle(player, "�2�lParab�ns!", "�oVoc� finalizou o �5�oMapMeel �f�ov4 com sucesso!", 20, 100, 20);
                    player.sendMessage(MapUtils.PREFIX + "�2�lParab�ns! �f�oVoc� finalizou o MapMeel v4 com sucesso!");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 50000, 1);
                    MapUtils.playSound(player, MapSound.EFFECT_WIN);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                MapUtils.sendPluginMessage(player, "MapMeelv4Complete");
                                MapUtils.sendPlayer(player, "KitPvP");
                            }
                        }
                    }.runTaskLater(MapMain.getPlugin(), 200);
                }

            }
        };

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), -15, 49, 43, -335, 4), Arrays.asList(
                "Oi!", "Eu estava lhe esperando...", "Seja bem-vindo ao castelo do MapMeel v4", "Infelizmente a mesma n�o se encontra agora",
                "Mas...", "Ok", "Vou falar", "A Meel foi raptada por um Wither", "e foi presa na torre do castelo", "ent�o voc� precisa ajuda-la para sair de l�!",
                "ela est� presa no �ltimo n�vel deste castelo", "mas para voc� ir abrindo as portas e port�es", "voc� vai precisar do poder das estrelas!",
                "no caso...", "do poder das meels!", "comece jogando pela 'Mountain Village'", "que a porta est� logo a atr�s de voc� a sua direita",
                "basta entrar na pintura que voc� entrar� na fase!",
                "Quando voc� conseguir pegar 1 estrela", "as outras portas v�o come�ar a abrir", "ent�o o que est� esperando?", "Boa sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 29, 49, -80, -72, -19), Arrays.asList(
                "Oi!", "Parece que voc� conseguiu mais algumas estrelas", "bem...", "Seja bem-vindo ao n�vel 2!",
                "Aqui voc� ir� passar por n�vel do mesmo estilo", "6 objetivos principais e o objetivo das 100 moedas!",
                "Basicamente voc� j� sabe como andar pelo mapa e como se virar", "ent�o boa sorte!",
                "ah, espere...", "eu ouvi falar por a�", "que possui estrelas secretas escondidas pelo mapa",
                "guardadas pelo Diego", "n�o sei se esses boatos s�o verdadeiros", "por que sempre tive medo de sair do castelo",
                "mas enfim", "Boa sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 29, 49, 30, -73, -19), Arrays.asList(
                "Oi!", "Seu progresso pelo mapa est� indo muito bem", "Parab�ns!", "mas n�o � hora de descanso", "precisamos salvar a Meel do Wither!",
                "Voc� acaba de chegar no n�vel 3", "n�o preciso dizer que basicamente � a mesma coisa", "6 objetivos principais, mais o das 100 moedas",
                "ah, estou come�ando a desconfiar que essa hist�ria", "das estrelas secretas s�o reais", "e eu desconfio que uma dessas",
                "est� no jardim desta torre", "tente depois subir at� l� com seu super pulo!", "mas enfim", "Boa Sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), -17, 50, -35, -45, 1), Arrays.asList(
                "Oi!", "Estou realmente impressionado com seu progresso!", "Uau!", "Mais de 50 estrelas!", "Mas enfim...",
                "N�o podemos negar o fato que a Meel continua presa com o Wither", "Vamos l�!", "Ah...", "Sobre as estrelas secretas?",
                "Pesquisando mais sobre", "descobri que as estrelas secretas n�o est�o s� aqui perto do castelo",
                "Podem est� no meio da floresta!", "Ent�o como eu tenho bastante medo de sair do castelo", "n�o posso te dizer com certeza...",
                "Mas segundo algumas fontes que eu pesquisei", "Possui no total 7 estrelas escondidas!", "Incluindo a do alto da torre sul!",
                "Mas enfim...", "Seja bem-vindo ao n�vel 4!", "Boa sorte no resgate da Meel!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), -6, 122, -29, -65, 1), Arrays.asList(
                "Oi!", "Voc� est� bem pr�ximo da fase do Wither!", "mas voc� precisa treinar...", "Treine nesta �ltima fase", "tenho certeza que estar� preparado!",
                "Logo depois use qualquer uma das esponjas e suba para a plataforma superior", "L� � a entrada da fase onde a Meel est� presa!",
                "R�pido!", "Ela precisa de voc�!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 2, 145, -18, -230, -1), Arrays.asList(
                "Oi!", "Preciso falar uma coisa...", "Que estou superando v�rios medos de est� aqui...",
                "Perto da entrada da fase que a Meel est� presa!", "Precisa fazer isso depressa", "Ela j� est� presa faz muito tempo!",
                "Ent�o o que est� esperando?", "V� e salve a Meel!", "Boa Sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 22, 100, 29, -52, -1), Arrays.asList(
                "Seja bem-vindo ao gramado da torre sul!", "Mua-hahahahaha", "Voc� nunca conseguir� subir nesta parede para a entrada!",
                "Mua-hahahahaha", "Na verdade consegue...", "S� dar um super-pulo...", "Mas enfim...",
                "Parab�ns!", "Voc� encontrou a fase de uma estrela escondida!", "Basicamente...", "Esta fase s� possui 1 objetivo!",
                "Nem o objetivo das 100 moedas � ativo nesta fase!", "o objetivo desta fase � �nica e exclusivamente...",
                "Voc� conseguir essa tal estrela secreta", "Mas enfim...", "N�o perca tempo e v� pegar a primeira estrela secreta!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 224, 209, -184, -29, 0), Arrays.asList(
                "Ah, oi!", "Eu estava...", "Enfim...", "Como voc� conseguiu subir at� aqui?",
                "Usando super pulo?", "Ele � suficiente para voc� ir pulando at� aqui?",
                "Nossa!", "Eu saiba que o super pulo era forte", "mas n�o o suficiente para chegar aqui!",
                "Seja bem-vindo a estrela secreta do drag�o!", "Acha que foi longa a sua subida?",
                "Ent�o boa sorte... kkkk"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 326, 52, -45, -153, -1), Arrays.asList(
                "Por que n�o uma fase de caverna?", "N�o se preocupe!", "� apenas 1 objetivo mesmo...",
                "Mas cuidado para n�o se perder nesta caverna!", "Minhas tentativas de pegar a estrela secreta desta fase",
                "n�o foram poucas n�o...", "Mas enfim", "Sei que n�o quer perder tanto tempo comigo", "ent�o n�o perca tempo e v�!",
                "Boa Sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 134, 52, -230, -32, 11), Arrays.asList(
                "Ah, oi!", "Eu estava fazendo uma reforma...", "nesta parte da floresta...",
                "Nada faz sentido aqui!", "Como assim uma entrada de �gua no meio do nada?",
                "Mas eu encontrei esta fase de estrela secreta!", "Como estava perto de �gua...",
                "O tema desta fase � templo da �gua", "mas n�o se preocupe", "n�o � do de baixo d'�gua",
                "O tempo submerso � em outro lado dos campos do castelo!", "por enquanto voc� n�o precisa de preocupar!",
                "Mas enfim...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), -94, 55, -278, -50, -1), Arrays.asList(
                "Mas olha s� quem apareceu!", "Oi meu chapa!", "Eu estava dando uma volta na floresta e acabei me perdendo...",
                "Ah sim...", "Uma pergunta...", "Como voc� fez para vir at� aqui?", "Estou perdido e sem saber como onde eu vou!",
                "At� que encontrei essa caverna quentinha para me aquecer...", "At� que na coindidencia", "POU!",
                "Encontrei uma fase de uma estrela secreta!", "Ent�o basicamente agora � com voc�!",
                "Minha �poca de encontrar estrelas escondidas acabou...",
                "Agora s� posto toda a minha experi�ncia no meu f�rum...",
                "N�o vou gastar mais do seu tempo...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 162, 118, 256, -31, 1), Arrays.asList(
                "A vis�o daqui realmente � bem bonita!", "Mais bonita do que da boca do drag�o...",
                "Mas n�o t�o alta quanto l�", "Sei l�...", "Aqui s� tem uma vis�o mais bonita!",
                "Parece at� com o apartamento do Bruno Co�lho que mora nos campos do castelo!", "Oi?",
                "Voc� n�o sabia que ele tem um apartamento escondido pelos campos do castelo?",
                "Bem...", "Eu n�o sei exatamente a localiza��o", "Mas eu vi em outros f�runs",
                "que esse apartamento � muito bem protegido!", "Apenas os guerreiros entram l�!",
                "Nunca tive a honra de ir conhecer esse apartamento escondido",
                "Precisa de muitas estrelas para poder abrir aquele port�o!",
                "Estrelas cujo a maioria est� no castelo", "e eu fui banido do castelo pelo Wither que prendeu a Meel!",
                "Acho que ele j� ouviu falar sobre as minhas hist�rias", "capturando as estrelas em geral",
                "Mas enfim...", "Hist�rias antigas...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), -161, 5, 44, -49, 2), Arrays.asList(
                "Glu", "Blu", "Klu", "Minha nossa...", "N�o acredito que voc� conseguiu descer isso tamb�m!",
                "Tinha visto uma passagem com luz l� de cima", "ent�o resolvi respirar fundo", "e tentar",
                "De inicio pensava que era uma passagem secreta", "para o apartamento escondido do Bruno Co�lho",
                "mas como dito no f�rum que pesquisei", "A entrada para o apartamento pelos campos do castelo do Bruno",
                "s� � poss�vel conseguindo muitas estrelas!", "e cujo a entrada eu n�o sei onde �...",
                "Mas segundo f�runs na DeepWeb", "diz que � pr�ximo a fase do tempo submerso de baixo d'�gua!",
                "Ent�o vim para c�!", "Mas n�o encontrei nada...", "Apenas uma quase morte afogado",
                "Mas enfim...", "O bom � eu to vivo!", "agora a fase da estrela secreta", "� como dito anteriormente!",
                "Esta � a fase secreta do tempo sumerso", "ent�o n�o perca mais tempo!", "Boa Sorte!"
        ));

    }

}
