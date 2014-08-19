package mina.echo;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class EchoServerHandler extends IoHandlerAdapter {
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("session created : " + session.getId());
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String _message = message.toString();
		if("bye".equalsIgnoreCase(_message)) {
			System.out.println("session " + session.getId() + " disconnect...");
			session.close(false);
			return;
		}
		
		System.out.println("received from " + session.getId() + " : " + _message);
		session.write(_message);
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		
//		session.close(false);
	}
}
