FROM docker.elastic.co/elasticsearch/elasticsearch:8.12.0


COPY plugins/analysis-nori-8.12.0.zip /tmp/
COPY plugins/jaso-analyzer-plugin-8.12.0-plugin.zip /tmp/

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///tmp/analysis-nori-8.12.0.zip
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///tmp/jaso-analyzer-plugin-8.12.0-plugin.zip