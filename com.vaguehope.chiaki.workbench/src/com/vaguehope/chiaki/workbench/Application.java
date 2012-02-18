package com.vaguehope.chiaki.workbench;

import java.util.concurrent.CountDownLatch;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public final class Application implements IApplication {

	private final CountDownLatch latch = new CountDownLatch(1);

	@Override
	public Object start (IApplicationContext context) throws Exception {
		context.applicationRunning();
		this.latch.await();
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop () {
		this.latch.countDown();
	}

}
