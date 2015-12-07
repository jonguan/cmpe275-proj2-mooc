/*
 * copyright 2012, gash
 * 
 * Gash licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package poke.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.comm.App;
import poke.comm.App.Request;
import poke.server.resources.Resource;
import poke.server.resources.ResourceUtil;

public class JobResource implements Resource {

	protected static Logger logger = LoggerFactory.getLogger("server");

	@Override
	public Request process(Request request) {
		// TODO Auto-generated method stub
		logger.info("poke: " + request.getBody().getPing().getTag());

		Request.Builder rb = Request.newBuilder();

		// metadata
		rb.setHeader(ResourceUtil.buildHeaderFrom(request.getHeader(), App.PokeStatus.SUCCESS, null));

		// payload
		App.Payload.Builder pb = App.Payload.newBuilder();
		App.Ping.Builder fb = App.Ping.newBuilder();
		fb.setTag(request.getBody().getPing().getTag());
		fb.setNumber(request.getBody().getPing().getNumber());
		pb.setPing(fb.build());
		rb.setBody(pb.build());

		Request reply = rb.build();

		logger.info("inside process of resource: "+reply.getBody());

		return reply;
	}

}
