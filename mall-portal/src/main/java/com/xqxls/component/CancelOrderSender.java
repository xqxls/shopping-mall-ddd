package com.xqxls.component;

/**
 * 取消订单消息的发出者
 * Created by macro on 2018/9/14.
 */
//@Component
//public class CancelOrderSender {
//    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderSender.class);
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    public void sendMessage(Long orderId,final long delayTimes){
//        //给延迟队列发送消息
//        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                //给消息设置延迟毫秒值
//                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
//                return message;
//            }
//        });
//        LOGGER.info("send orderId:{}",orderId);
//    }
//}
