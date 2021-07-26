package br.com.alelo.consumer.consumerpat.util;

public class Constants {

    private Constants(){}

    public static class Codes{
        public final static String CODE_OK = "0";
        public final static String CODE_NG = "1";
        public final static String CODE_FAIL = "5";
    }

    public static class Links{
        public static String APP_URL = "http://localhost:8080/consumers";
    }

    public static class Errors{
        public final static String RECOVER_ALL_CUSTOMERS = "Não foi possível recuperar a lista de clientes, tente novamente mais tarde";
        public final static String RECOVER_CUSTOMER = "Não foi possível localizar este usuário, tente novamente mais tarde";
        public final static String CREATE_CUSTOMER = "Não foi possível concluír seu cadastro, tente novamente mais tarde";
        public final static String UPDATE_CUSTOMER = "Não foi possível concluír seu cadastro, tente novamente mais tarde";
        public final static String CUSTOMER_ALREADY_EXISTS = "Este cliente já está cadastrado em nossa base de dados";

        public final static String SAVE_UPDATE_CONTACT_CUSTOMER = "Não foi possível concluír sua solicitação";
        public final static String RECOVER_RECORD_CONSUMER = "Não foi possível localizar este registro para este usuário.";
        public final static String RECOVER_CARD_CONSUMER = "Não foi possível localizar este registro para este usuário.";

        public final static String RECOVER_CARD = "Cartão não foi localizado";

        public final static String AUTH_INVALID = "Acesso negado";

        public final static String CREATE_EXTRACT = "Não foi possível concluír sua compra, tente novamente mais tarde";
        public final static String INVALID_ESTABLISHMENT_CODE = "Código do estabelecimento inválido";
        public final static String INSUFICIENT_BALANCE = "Saldo insuficiente";

        public final static String BUSINESS_SERROR = "Não foi possível concluír sua solicitação, tente novamente mais tarde";
    }

    public static class Success{
        public final static String API_OK = "Solicitação concluída com sucesso";
    }

    public static class Numbers{
        public static final Integer ONE_HUNDRED = 100;
    }

}
