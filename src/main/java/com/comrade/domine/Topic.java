package com.comrade.domine;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Topic(Integer topicId, String name)  implements TopicBuilder.With{

}
