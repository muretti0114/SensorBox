#!/usr/bin/perl -w
#
# SensorBoxServiceClient.pl
# Data logger for SensorBoxService that posts the data to fluentd proxy
#
# 2015-09-01 Masahide Nakamura
#
# (Note) There are many "hard-coded parameters".
#

use XML::Simple;
use LWP::Simple;
use Data::Dumper;
use HTTP::Request::Common;

#################################################
# Static parameters for Logger
my $sensorId = 'jp.kobe_u.sensor.sensorbox.nakamura02';
my $location = 'Grenoble Ensimag D-316';
my $type = 'Environment';
my $tag = "global.sensorbox.grenoble";
my $outFile = ">sensorboxclient.out";
my $INTERVAL = 10;  # 10 sec
#################################################


while (1) {
	my $json = &generateJson();
	open $out, $outFile  || die "Cannot open file: $outFile";
	print $out $json;
	#print STDERR $json, "\n";
	close $out;
	&postData ($json);
	sleep $INTERVAL - 1;
}


# Execute HTTP POST to fluentd proxy
sub postData {
	my $data = shift;
	my $url = 'http://133.30.159.3/~masa-n/FluentdProxy/proxy.cgi';
	# POST準備
	my $ua = LWP::UserAgent -> new;
	
	my $res = $ua->request(POST $url,
		[
			p => 'renkon',
			j => $data,
			t => $tag,
		]
	)->as_string;
	
}

## Assemble JSON data manually...
sub generateJson {
	my $json = "";

	# Date-Time information
	my ($sec,$min,$hh,$dd,$mm,$yy,$weak,$yday,$opt) = gmtime();
	$mm +=1; $yy +=1900;

	my $date = sprintf "%4d-%02d-%02d", $yy, $mm, $dd;
	my $timeOfDay = sprintf "%02d:%02d:%02d", $hh, $min, $sec;

	# Get sensor values and appliance status
	my $data = &getData();

	$json = "{\"sensorId\":\"$sensorId\", \"location\":\"$location\",\"type\":\"$type\","
		 ."\"date\":\"$date\", \"timeOfDay\":\"$timeOfDay\","."\"data\":{$data}}";

	return $json;
}

# Get data from Web services. Horrible hard code...
sub getData {
	my $str="";
	#my $boxIPAddr="129.88.49.247";
	my $boxIPAddr="localhost";
	
	
	# SensorBox 
	my $url = "http://$boxIPAddr:8080/axis2/services/SensorBoxService/getAllValues";
	my $res = get $url;
	my $xs = new XML::Simple(KeyAttr => []);
	my $dom = $xs->XMLin($res);
	foreach my $item (@{$dom->{return}}) {
		my $id = $item->{id};
		my $val = $item->{value};
		$str = $str . "\"".$id."\":\"".$val."\",";
	}
	
	# FanStatus
	$url = "http://$boxIPAddr:8080/axis2/services/D316FanService/getStatus";
	$res = get $url;
	$xs = new XML::Simple(KeyAttr => []);
	$dom = $xs->XMLin($res);
	my $fanPower = $dom->{return}->{power};
	$str = $str . "\"fanPower\":\"".$fanPower."\",";

	# WindowStatus
	$url = "http://$boxIPAddr:8080/axis2/services/D316WindowService/getAllStatus";
	$res = get $url;
	$xs = new XML::Simple(KeyAttr => []);
	$dom = $xs->XMLin($res);
	
	for (my $i=0; $i<3; $i++) {
		my $id = "window".$i;
		my $val = $dom->{return}->[$i]->{state};
		$str = $str . "\"".$id."\":\"".$val."\",";
	}
	$str =~ s/,$//;
	return $str;
}

