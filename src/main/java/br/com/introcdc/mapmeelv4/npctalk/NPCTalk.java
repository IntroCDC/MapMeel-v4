package br.com.introcdc.mapmeelv4.npctalk;
/*
 * Written by IntroCDC, Bruno Coêlho at 26/04/2019 - 05:42
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
                    otherPlayer.sendMessage("§b§o§l" + getName() + "§f: " + message);
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
                "Ah, oi!", "Parece que você terminou o mapa não é mesmo?", "Enfim, primeiro...", "Parabéns!", "Segundo...", "Bem, este é o fim do mapa",
                "O objetivo era mesmo as 120 estrelas", "no caso", "120 meels", "(piadão)", "então não possui mais nenhum objetivo disponível para você jogar",
                "Por que eu estava aqui?", "Não sei exatamente...", "Eu gosto de ficar aqui no meu apartamento...",
                "Apenas adimirando a vista", "Desde quando a história do MapMeel acabou", "Não sei...",
                "Eu estive meio parado...", "parece que o Intro morreu junto com a história",
                "Aquele antigo Intro apaixonado simplesmente morreu", "com vários traumas e decepções",
                "Então por isso que me demonstro como Bruno agora", "pois o Intro morreu",
                "e agora quem voltou foi o Bruno, Bruno Coêlho"
                , "Mas enfim", "Você!", "Você é um guerreiro!",
                "Por ter passado na raça todo o MapMeel v4!", "Pra falar a verdade...", "Eu nunca esperava que aqueles portões fossem ser abertos!",
                "Eu não sei", "Eu acho que ninguém iria ter paciência de chegar até aqui", "Mas parece que você foi diferente",
                "Muito obrigado por compreender meu trabalho", "principalmente neste mapa",
                "Que basicamente não tem objetivo nenhum", "Já que a Meel nunca terminou o MapMeel v3",
                "Qual seria a chance dela querer terminar o MapMeel v4 kk", "Sabe qual é o pior?",
                "É que eu já pensei em fazer um MapMeel v5", "Mas... o que eu faria agora?",
                "MapMeel v1 é um carrinho", "MapMeel v2 e v3 é parkour", "e esse é uma junção de Super Mario 64",
                "Com Gex - Get The Encko", "Não sei", "Eu já cheguei a ter ideia pro MapMeel v5", "Mas não tem história para contar",
                "Pois o v1 é contando 2014", "v2 contando 2015", "v3 contando 2016", "e esse na teoria seria 2017",
                "mas a história mesmo termina no início de 2017", "e os últimos momentos já foram mostrados no MapMeel v3",
                "Se eu fosse fazer um MapMeel v5", "seria no total foda-se, caguei 3 vezes", "sabe qual é o pior de novo?",
                "Eu já cheguei a tentar começar kkkk", "Tem um servidor na máquina", "mas só tem um lobby inacabado",
                "Eu acho...", "que eu não vou continuar ele kk", "se for pra fazer algum MapMeel",
                "Vai ser do v4 para baixo", "não o v5 kk", "Mas enfim...",
                "Então...",
                "Sim!", "Obrigado por jogar no MapMeel v4!", "Parabéns, você finalizou o MapMeel v4 com sucesso!", ":)", ";)", ":D", ";D", "."
        )) {

            @Override
            public void postTalk() {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    MapUtils.sendTitle(player, "§2§lParabéns!", "§oVocê finalizou o §5§oMapMeel §f§ov4 com sucesso!", 20, 100, 20);
                    player.sendMessage(MapUtils.PREFIX + "§2§lParabéns! §f§oVocê finalizou o MapMeel v4 com sucesso!");
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
                "Oi!", "Eu estava lhe esperando...", "Seja bem-vindo ao castelo do MapMeel v4", "Infelizmente a mesma não se encontra agora",
                "Mas...", "Ok", "Vou falar", "A Meel foi raptada por um Wither", "e foi presa na torre do castelo", "então você precisa ajuda-la para sair de lá!",
                "ela está presa no último nível deste castelo", "mas para você ir abrindo as portas e portões", "você vai precisar do poder das estrelas!",
                "no caso...", "do poder das meels!", "comece jogando pela 'Mountain Village'", "que a porta está logo a atrás de você a sua direita",
                "basta entrar na pintura que você entrará na fase!",
                "Quando você conseguir pegar 1 estrela", "as outras portas vão começar a abrir", "então o que está esperando?", "Boa sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 29, 49, -80, -72, -19), Arrays.asList(
                "Oi!", "Parece que você conseguiu mais algumas estrelas", "bem...", "Seja bem-vindo ao nível 2!",
                "Aqui você irá passar por nível do mesmo estilo", "6 objetivos principais e o objetivo das 100 moedas!",
                "Basicamente você já sabe como andar pelo mapa e como se virar", "então boa sorte!",
                "ah, espere...", "eu ouvi falar por aí", "que possui estrelas secretas escondidas pelo mapa",
                "guardadas pelo Diego", "não sei se esses boatos são verdadeiros", "por que sempre tive medo de sair do castelo",
                "mas enfim", "Boa sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 29, 49, 30, -73, -19), Arrays.asList(
                "Oi!", "Seu progresso pelo mapa está indo muito bem", "Parabéns!", "mas não é hora de descanso", "precisamos salvar a Meel do Wither!",
                "Você acaba de chegar no nível 3", "não preciso dizer que basicamente é a mesma coisa", "6 objetivos principais, mais o das 100 moedas",
                "ah, estou começando a desconfiar que essa história", "das estrelas secretas são reais", "e eu desconfio que uma dessas",
                "está no jardim desta torre", "tente depois subir até lá com seu super pulo!", "mas enfim", "Boa Sorte!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), -17, 50, -35, -45, 1), Arrays.asList(
                "Oi!", "Estou realmente impressionado com seu progresso!", "Uau!", "Mais de 50 estrelas!", "Mas enfim...",
                "Não podemos negar o fato que a Meel continua presa com o Wither", "Vamos lá!", "Ah...", "Sobre as estrelas secretas?",
                "Pesquisando mais sobre", "descobri que as estrelas secretas não estão só aqui perto do castelo",
                "Podem está no meio da floresta!", "Então como eu tenho bastante medo de sair do castelo", "não posso te dizer com certeza...",
                "Mas segundo algumas fontes que eu pesquisei", "Possui no total 7 estrelas escondidas!", "Incluindo a do alto da torre sul!",
                "Mas enfim...", "Seja bem-vindo ao nível 4!", "Boa sorte no resgate da Meel!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), -6, 122, -29, -65, 1), Arrays.asList(
                "Oi!", "Você está bem próximo da fase do Wither!", "mas você precisa treinar...", "Treine nesta última fase", "tenho certeza que estará preparado!",
                "Logo depois use qualquer uma das esponjas e suba para a plataforma superior", "Lá é a entrada da fase onde a Meel está presa!",
                "Rápido!", "Ela precisa de você!"
        ));

        new NPCTalk("SombraXD", new Location(Bukkit.getWorld("world"), 2, 145, -18, -230, -1), Arrays.asList(
                "Oi!", "Preciso falar uma coisa...", "Que estou superando vários medos de está aqui...",
                "Perto da entrada da fase que a Meel está presa!", "Precisa fazer isso depressa", "Ela já está presa faz muito tempo!",
                "Então o que está esperando?", "Vá e salve a Meel!", "Boa Sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 22, 100, 29, -52, -1), Arrays.asList(
                "Seja bem-vindo ao gramado da torre sul!", "Mua-hahahahaha", "Você nunca conseguirá subir nesta parede para a entrada!",
                "Mua-hahahahaha", "Na verdade consegue...", "Só dar um super-pulo...", "Mas enfim...",
                "Parabéns!", "Você encontrou a fase de uma estrela escondida!", "Basicamente...", "Esta fase só possui 1 objetivo!",
                "Nem o objetivo das 100 moedas é ativo nesta fase!", "o objetivo desta fase é única e exclusivamente...",
                "Você conseguir essa tal estrela secreta", "Mas enfim...", "Não perca tempo e vá pegar a primeira estrela secreta!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 224, 209, -184, -29, 0), Arrays.asList(
                "Ah, oi!", "Eu estava...", "Enfim...", "Como você conseguiu subir até aqui?",
                "Usando super pulo?", "Ele é suficiente para você ir pulando até aqui?",
                "Nossa!", "Eu saiba que o super pulo era forte", "mas não o suficiente para chegar aqui!",
                "Seja bem-vindo a estrela secreta do dragão!", "Acha que foi longa a sua subida?",
                "Então boa sorte... kkkk"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 326, 52, -45, -153, -1), Arrays.asList(
                "Por que não uma fase de caverna?", "Não se preocupe!", "É apenas 1 objetivo mesmo...",
                "Mas cuidado para não se perder nesta caverna!", "Minhas tentativas de pegar a estrela secreta desta fase",
                "não foram poucas não...", "Mas enfim", "Sei que não quer perder tanto tempo comigo", "então não perca tempo e vá!",
                "Boa Sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 134, 52, -230, -32, 11), Arrays.asList(
                "Ah, oi!", "Eu estava fazendo uma reforma...", "nesta parte da floresta...",
                "Nada faz sentido aqui!", "Como assim uma entrada de água no meio do nada?",
                "Mas eu encontrei esta fase de estrela secreta!", "Como estava perto de água...",
                "O tema desta fase é templo da água", "mas não se preocupe", "não é do de baixo d'água",
                "O tempo submerso é em outro lado dos campos do castelo!", "por enquanto você não precisa de preocupar!",
                "Mas enfim...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), -94, 55, -278, -50, -1), Arrays.asList(
                "Mas olha só quem apareceu!", "Oi meu chapa!", "Eu estava dando uma volta na floresta e acabei me perdendo...",
                "Ah sim...", "Uma pergunta...", "Como você fez para vir até aqui?", "Estou perdido e sem saber como onde eu vou!",
                "Até que encontrei essa caverna quentinha para me aquecer...", "Até que na coindidencia", "POU!",
                "Encontrei uma fase de uma estrela secreta!", "Então basicamente agora é com você!",
                "Minha época de encontrar estrelas escondidas acabou...",
                "Agora só posto toda a minha experiência no meu fórum...",
                "Não vou gastar mais do seu tempo...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), 162, 118, 256, -31, 1), Arrays.asList(
                "A visão daqui realmente é bem bonita!", "Mais bonita do que da boca do dragão...",
                "Mas não tão alta quanto lá", "Sei lá...", "Aqui só tem uma visão mais bonita!",
                "Parece até com o apartamento do Bruno Coêlho que mora nos campos do castelo!", "Oi?",
                "Você não sabia que ele tem um apartamento escondido pelos campos do castelo?",
                "Bem...", "Eu não sei exatamente a localização", "Mas eu vi em outros fóruns",
                "que esse apartamento é muito bem protegido!", "Apenas os guerreiros entram lá!",
                "Nunca tive a honra de ir conhecer esse apartamento escondido",
                "Precisa de muitas estrelas para poder abrir aquele portão!",
                "Estrelas cujo a maioria está no castelo", "e eu fui banido do castelo pelo Wither que prendeu a Meel!",
                "Acho que ele já ouviu falar sobre as minhas histórias", "capturando as estrelas em geral",
                "Mas enfim...", "Histórias antigas...", "Boa sorte!"
        ));

        new NPCTalk("DiegoSVP", new Location(Bukkit.getWorld("world"), -161, 5, 44, -49, 2), Arrays.asList(
                "Glu", "Blu", "Klu", "Minha nossa...", "Não acredito que você conseguiu descer isso também!",
                "Tinha visto uma passagem com luz lá de cima", "então resolvi respirar fundo", "e tentar",
                "De inicio pensava que era uma passagem secreta", "para o apartamento escondido do Bruno Coêlho",
                "mas como dito no fórum que pesquisei", "A entrada para o apartamento pelos campos do castelo do Bruno",
                "só é possível conseguindo muitas estrelas!", "e cujo a entrada eu não sei onde é...",
                "Mas segundo fóruns na DeepWeb", "diz que é próximo a fase do tempo submerso de baixo d'água!",
                "Então vim para cá!", "Mas não encontrei nada...", "Apenas uma quase morte afogado",
                "Mas enfim...", "O bom é eu to vivo!", "agora a fase da estrela secreta", "é como dito anteriormente!",
                "Esta é a fase secreta do tempo sumerso", "então não perca mais tempo!", "Boa Sorte!"
        ));

    }

}
