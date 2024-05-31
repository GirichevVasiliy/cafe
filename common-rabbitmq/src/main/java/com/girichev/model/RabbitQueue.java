package com.girichev.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RabbitQueue {
    public static final String EXCHANGE_NAME = "cafe_topic_exchange";

    // Очереди
    public static final String QUEUE_CLIENT_TO_SHOP = "queue_client_to_cafe";
    public static final String QUEUE_SHOP_TO_CLIENT = "queue_cafe_to_client";


    // routingKey
    public static final String ROUTING_KEY_CLIENT_TO_SHOP = "Client.send.message.cafe";
    public static final String ROUTING_KEY_SHOP_TO_CLIENT = "Cafe.send.message.client";

    //Различные статусы, по которым
    public static final String TYPE_CANCELED_ORDER_CLIENT = "canceled_order_client";
    public static final String TYPE_CHECKOUT_ORDER_CLIENT = "checkout_order_client";

    /**
     * Обменник для проблемных сообщений, он будет топик и иметь 4 очереди.
     * Суть работы: Клиент отправил сообщение и оно было не доставлено n-раз или обработано с ошибкой
     */
    public static final String EXCHANGE_NAME_DLX = "cafe_topic_exchange_dlx";

    // Настройка очереди
    public static final int DELIVERY_LIMIT = 3;

    // Обменник 4 очереди для получения проблемных сообщений
    public static final String QUEUE_CLIENT_ERROR_MESSAGE = "queue_client_error_message";
    public static final String QUEUE_SHOP_ERROR_MESSAGE = "queue_cafe_error_message";


    // Обменник топик, и его очереди будут ловить любые сообщения начинающиеся на это слово
    public static final String ROUTING_KEY_CLIENT_ERROR_MESSAGE = "Client.#";
    public static final String ROUTING_KEY_SHOP_ERROR_MESSAGE = "Cafe.#";

}
